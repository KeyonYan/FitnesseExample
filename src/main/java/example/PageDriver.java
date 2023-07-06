package example;

import java.util.HashMap;

public class PageDriver {

    private static HashMap<String, String> pages = new HashMap<>();
    private static String currentPageName;
    private static String[][] patterns = {
            {"'''(\\w*)'''", "<b>$1</b>"},
            {"''(\\w*)''", "<i>$1</i>"},
    };

    public void createPageWithContent(String pageName, String content) throws Exception {
        for (String[] pattern : patterns) {
            content = content.replaceAll(pattern[0], pattern[1]);
        }
        pages.put(pageName, content);
    }

    public int requestPage(String uri) throws Exception {
        if (pages.containsKey(uri)) {
            currentPageName = uri;
            return 200;
        }
        return 404;
    }

    public boolean contentMatches(String pattern) throws Exception {
        String htmlText = pages.get(currentPageName);
        return htmlText.equals(pattern);
    }

    public String content() throws Exception {
        return pages.get(currentPageName);
    }
}
