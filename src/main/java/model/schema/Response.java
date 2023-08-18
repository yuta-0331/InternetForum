package model.schema;

import java.util.function.Consumer;

public class Response {
    private int responseId;
    private int userId;
    private String userName;
    private int threadId;
    private String description;
    private String postedDate;
    private String update;
    private boolean deleteFlag;
    private boolean report;
    
    public int getResponseId() {
        return responseId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getThreadId() {
        return threadId;
    }

    public String getDescription() {
        return description;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public String getUpdate() {
        return update;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public boolean isReport() {
        return report;
    }

    private Response(Builder builder) {
        this.responseId = builder.responseId;
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.threadId = builder.threadId;
        this.description = builder.description;
        this.postedDate = builder.postedDate;
        this.update = builder.update;
        this.deleteFlag = builder.deleteFlag;
        this.report = builder.report;
    }
    
    public static class Builder {
        private int responseId;
        public int userId;
        public String userName;
        public int threadId;
        public String description;
        public String postedDate;
        public String update;
        public boolean deleteFlag;
        public boolean report;
        
        public Builder(int responseId) {
            this.responseId = responseId;
        }
        
        public Builder with(Consumer<Builder> builder) {
            builder.accept(this);
            return this;
        }
        public Response build() {
            return new Response(this);
        }
    }
}
