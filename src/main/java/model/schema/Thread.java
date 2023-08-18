package model.schema;

import java.util.function.Consumer;

public class Thread {
    private int threadId;
    private int userId;
    private String userName;
    private String createDay;
    private String title;
    private String desc;
    private String update;
    private boolean deleteFlag;
    private int genreId;
    private String genreName;
    private boolean report;
    private String lastWrittenDate; // 最終書き込み日
    
    public int getThreadId() {
        return threadId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCreateDay() {
        return createDay;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getUpdate() {
        return update;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public boolean isReport() {
        return report;
    }
    
    public String getLastWrittenDate() {
        return lastWrittenDate;
    }

    private Thread(Builder builder) {
        this.threadId = builder.threadId;
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.createDay = builder.createDay;
        this.title = builder.title;
        this.desc = builder.desc;
        this.update = builder.update;
        this.deleteFlag = builder.deleteFlag;
        this.genreId = builder.genreId;
        this.genreName = builder.genreName;
        this.report = builder.report;
        this.lastWrittenDate = builder.lastWrittenDate;
    }
    
    public static class Builder {
        private int threadId;
        public int userId;
        public String userName;
        public String createDay;
        public String title;
        public String desc;
        public String update;
        public boolean deleteFlag;
        public int genreId;
        public String genreName;
        public boolean report;
        public String lastWrittenDate;
        
        public Builder(int threadId) {
            this.threadId = threadId;
        }
        
        public Builder with(Consumer<Builder> builder) {
            builder.accept(this);
            return this;
        }
        public Thread build() {
            return new Thread(this);
        }
    }
}
