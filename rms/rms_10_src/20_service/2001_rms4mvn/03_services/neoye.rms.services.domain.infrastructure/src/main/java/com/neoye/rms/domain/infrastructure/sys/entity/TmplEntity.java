package com.neoye.rms.domain.infrastructure.sys.entity;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.cmpnt.libs.commons.exception.BeanUtilException;
import rms.cmpnt.libs.commons.log.Log;
import rms.cmpnt.libs.commons.util.BeanUtil;

import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplSrvitemDTO;
import com.neoye.rms.domain.infrastructure.sys.repo.TmplRepo;

@Service
@Transactional
public class TmplEntity {

	@Autowired
	private TmplRepo tmplRepo;

	private Log log = Log.getClassLogger(TmplEntity.class);

	public String getDescription4Tmpl(TmplDTO tmplDto, Object obj) {
		TmplDTO argsDTO = getTmpl(tmplDto);
		return tmpl2Str(argsDTO, obj);
	}

	@Cacheable(value = "DIC_CACHE", key = "#tmplSrvitemVO.tmplType + #tmplSrvitemVO.serverItemId")
	public int getTmplIdBysevItem(TmplSrvitemDTO tmplSrvitemDto) {
		return tmplRepo.retrieveTmplIdBySevItem(tmplSrvitemDto);
	}

	private TmplDTO getTmpl(TmplDTO tmplDto) {
		return tmplRepo.retrieveTemplate(tmplDto);
	}

	private String tmpl2Str(TmplDTO alarmTmplDto, Object obj) {
		if (obj == null || alarmTmplDto == null ) {
			return null;
		}
		String tmplValue = alarmTmplDto.getTmplValue();
		Map objMap = getMapByObj(obj);
		Iterator it = objMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			String keyTmp = "#" + entry.getKey() + "#";
			if (entry.getValue() != null) {
				if (entry.getValue() instanceof String
						|| entry.getValue() instanceof Integer) {
					if (!entry.getValue().equals("")) {
						if (tmplValue.contains(keyTmp)) {
							tmplValue = tmplValue.replace(keyTmp, entry
									.getValue().toString());
						}
					}
				}
			} else {
				tmplValue = tmplValue.replace(keyTmp, "");
			}
		}
		// String result = MessageFormat.format(tmplValue, args);
		return tmplValue;
	}

	private Map<String, Object> getMapByObj(Object obj) {
		Map<String, Object> objMap = null;
		if (obj == null) {
			return null;
		}
		if (obj instanceof Map) {
			objMap = (Map<String, Object>) obj;
		} else {
			try {
				objMap = BeanUtil.getObjectField(obj);
			} catch (BeanUtilException e) {
				e.printStackTrace();
			}
		}

		return objMap;
	}
}
