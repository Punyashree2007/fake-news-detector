public class NewsArticle {
    private String title;
    private String content;
    private String source;

    public NewsArticle(String title, String content, String source) {
        this.title = title;
        this.content = content;
        this.source = source;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getSource() { return source; }
}