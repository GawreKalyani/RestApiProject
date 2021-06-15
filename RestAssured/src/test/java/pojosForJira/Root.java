package pojosForJira;

import java.util.List;

public class Root {
		private int startAt;
	    private int maxResults;
	    private int total;
	    private List<Comment> comments;
	    
	    
	    public int getStartAt() {
			return startAt;
		}
	    public int getMaxResults() {
			return maxResults;
		}
	    public int getTotal() {
			return total;
		}
	    public List<Comment> getComments() {
			return comments;
		}
}
