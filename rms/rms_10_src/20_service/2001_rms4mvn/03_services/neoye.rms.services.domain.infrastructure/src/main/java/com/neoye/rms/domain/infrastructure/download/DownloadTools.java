package com.neoye.rms.domain.infrastructure.download;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Disposition;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.engine.header.Header;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.exception.ServiceException;
import rms.cmpnt.libs.commons.log.Log;

import com.neoye.rms.domain.infrastructure.base.RootPathTool;
import com.neoye.rms.domain.infrastructure.constant.InfrConstant;
import com.neoye.rms.domain.infrastructure.exception.DownloaderException;

@Service("/download")
public class DownloadTools extends ServerResource {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RootPathTool rootPathTool;

    private Log log = Log.getClassLogger(DownloadTools.class);

    @Override
    public void init(Context context, Request request, Response response) {
        super.init(context, request, response);
    }

    @Get
    public Representation represent() throws DownloaderException {
        Form form = getQuery();
        String category = getCategory(form);
        Map<String, String> queries = getQueryValues(form);
//        if (applicationContext.getBean(category) == null) {
//            log.warn("寻找资源下载处理方式失败");
//            return null;
//        }
        FileDTO cdrs = null;
        if (category == null || category.length() <= 0) {// 如果直接传入Path则不需要指定category特殊处理
            String path = getValueByKey(form, InfrConstant.DOWNLOAD_COMMON_PATH);
            String relPath = getValueByKey(form, InfrConstant.DOWNLOAD_COMMON_RELPATH);
            //处理中文乱码问题
            try {
                path = (path!=null&&path.length()>=0)?new String(path.getBytes("iso-8859-1"),"gbk"):null;
                relPath =  (relPath!=null&&relPath.length()>=0)?new String(relPath.getBytes("iso-8859-1"),"gbk"):null;
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new ServiceException("文件路径错误！"+e.getMessage());
            }
            
            cdrs = new FileDTO();
            if (relPath != null && relPath.length() >= 0)//传相对路径
                cdrs.setPath(rootPathTool.getRootDir() + relPath);
            else if (path != null && path.length() > 0)//传绝对路径
                cdrs.setPath(path);
            else {
                log.warn("下载文件参数错误！");
                return null;
            }
        }
        else {
            IDownloader commonDownload = (IDownloader) applicationContext.getBean(category);
            cdrs = commonDownload.downloadFile(queries);
            if (cdrs == null) throw new ServiceException("导出文件失败，请联系管理员！");
            String autoDel = queries.get(InfrConstant.DOWNLOAD_AUTO_DEL);
            if ("1".equals(autoDel)) {
            	cdrs.setAutoDel(true);
            }
        }
        FileRepresentation r = new FileRepresentation(cdrs.getPath(), MediaType.ALL);
        Disposition disposition = getDisposition(cdrs);
        r.setDisposition(disposition);
        r.setAutoDeleting(cdrs.isAutoDel());

        Series<Header> headers = (Series<Header>) getRequest().getAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
        Header header = new Header();
        header.setName(HeaderConstants.HEADER_CONTENT_DISPOSITION);
        header.setValue(cdrs.getPath().substring(cdrs.getPath().lastIndexOf("/") + 1));
        headers.add(header);
        getRequest().getAttributes().put(HeaderConstants.ATTRIBUTE_HEADERS, headers);

        return r;
    }

    private String getCategory(Form form) {
        return form.getFirstValue(InfrConstant.DOWNLOAD_CATEGORY);
    }

    private String getValueByKey(Form form, String key) {
        return form.getFirstValue(key);
    }

    private Map<String, String> getQueryValues(Form form) {
        Map<String, String> quaries = form.getValuesMap();
        quaries.remove(InfrConstant.DOWNLOAD_CATEGORY);
        return quaries;
    }

    private Disposition getDisposition(FileDTO crs) {
        File file = new File(crs.getPath());
        if (!file.exists()) {
            return null;
        }
        Disposition disp = new Disposition(Disposition.TYPE_ATTACHMENT);
        String fileName;
        try {
            // fileName = new String(file.getName().getBytes(), "UTF-8");
            // fileName = new String(file.getName().getBytes(), "ISO8859-1");
            // fileName = new String(file.getName().getBytes(), "GBK");
            fileName = URLEncoder.encode(file.getName(), "UTF-8");
            log.info("待下载的文件名为：" + fileName);
        }
        catch (UnsupportedEncodingException e) {
            log.warn("获取文件名转换编码错误");
            fileName = file.getName();
        }
        disp.setFilename(fileName);
        disp.setSize(file.length());
        return disp;
    }

}
