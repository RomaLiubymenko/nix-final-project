package ua.com.alevel.enumeration;

public enum ControllerLogEnum {

    NEW_LOG("Request to create {}: {}"),
    UPDATED_BY_UUID_LOG("Request to update {} by uuid {} : {}"),
    DELETED_BY_UUID_LOG("Request to delete {} by uuid {}"),
    DELETED_BY_UUIDS_LOG("Request to delete {} by uuids {}"),
    GET_BY_UUID_LOG("Request to get {} by uuid {}"),
    GET_ALL_LOG("Request to get all {}s"),
    GET_ALL_PAGE_LOG("Request to get page {}s based on params: pagination {}"),
    GET_ALL_PAGE_FOR_QUERY_LOG("Request to get page {}s based on params: pagination {}, query {}"),
    GET_ALL_PAGE_FOR_FILTER_LOG("Request to get page {}s based on params: pagination {}, filter {}"),
    GET_ALL_PAGE_FOR_FILTER_QUERY_LOG("Request to get page {}s based on params: pagination {}, filter {}, query {}");

    private final String logInfo;

    ControllerLogEnum(String logInfo) {
        this.logInfo = logInfo;
    }

    public String getLogInfo() {
        return logInfo;
    }
}
