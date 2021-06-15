package pojosForJira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvatarUrls {
	 @JsonProperty("48x48") 
	    private String _48x48;
	    @JsonProperty("24x24") 
	    private String _24x24;
	    @JsonProperty("16x16") 
	    private String _16x16;
	    @JsonProperty("32x32") 
	    private String _32x32;
	    
		public String get_48x48() {
			return _48x48;
		}
		public String get_24x24() {
			return _24x24;
		}
		public String get_16x16() {
			return _16x16;
		}
		public String get_32x32() {
			return _32x32;
		}
	    
}
