package com.example.democratics.COI.model;

public class modelClause {
    String ClauseNo;
    String ClauseDesc;
    modelSubClauses SubClauses;
    String Status;
    String FollowUp;

    public String getClauseNo() {
        return ClauseNo;
    }

    public void setClauseNo(String clauseNo) {
        ClauseNo = clauseNo;
    }

    public String getClauseDesc() {
        return ClauseDesc;
    }

    public void setClauseDesc(String clauseDesc) {
        ClauseDesc = clauseDesc;
    }

    public modelSubClauses getSubClauses() {
        return SubClauses;
    }

    public void setSubClauses(modelSubClauses subClauses) {
        SubClauses = subClauses;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getFollowUp() {
        return FollowUp;
    }

    public void setFollowUp(String followUp) {
        FollowUp = followUp;
    }
}
