package com.neoye.rms.domain.infrastructure.sys.intf.dto;


import org.springframework.stereotype.Service;

import com.neoye.rms.domain.infrastructure.base.BaseDTO;

@Service
public class LanguageDTO extends BaseDTO {
    

    /**
     * 
     */
    private static final long serialVersionUID = 7597679136123588304L;

    private Integer languageId;

    private String lanShortname;

    private String lanCName;

    private String lanEName;

    private Integer inUse;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanShortname() {
        return lanShortname;
    }

    public void setLanShortname(String lanShortname) {
        this.lanShortname = lanShortname;
    }

    public String getLanCName() {
        return lanCName;
    }

    public void setLanCName(String lanCName) {
        this.lanCName = lanCName;
    }

    public String getLanEName() {
        return lanEName;
    }

    public void setLanEName(String lanEName) {
        this.lanEName = lanEName;
    }

    public Integer getInUse() {
        return inUse;
    }

    public void setInUse(Integer inUse) {
        this.inUse = inUse;
    }

}