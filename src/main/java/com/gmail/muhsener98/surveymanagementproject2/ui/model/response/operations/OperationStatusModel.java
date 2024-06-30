package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations;

public class OperationStatusModel {

    private OperationNames operationName ;
    private OperationStatus operationStatus;

    public OperationStatusModel(OperationNames operationName, OperationStatus operationStatus) {
        this.operationName = operationName;
        this.operationStatus = operationStatus;
    }

    public OperationNames getOperationName() {
        return operationName;
    }

    public void setOperationName(OperationNames operationName) {
        this.operationName = operationName;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
