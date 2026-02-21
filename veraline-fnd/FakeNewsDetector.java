public class FakeNewsDetector {
    private String[] fakeWords = {"clickbait", "shocking", "rumor", "fake", "unverified", "conspiracy"};
    private String[] trustedSources = {"NDTV", "BBC", "The Hindu", "Times of India", "Youtube"};

    public String detect(NewsArticle article) {
        String text = (article.getTitle() + " " + article.getContent()).toLowerCase();

        for (String word : fakeWords) {
            if (text.contains(word)) {
                return "FAKE NEWS";
            }
        }

        for (String source : trustedSources) {
            if (article.getSource().equalsIgnoreCase(source)) {
                return "REAL NEWS";
            }
        }

        return "POSSIBLY FAKE";
    }
}