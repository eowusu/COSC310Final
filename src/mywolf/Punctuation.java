package mywolf;

public class Punctuation {
        
        /**
         * The main purpose of this class is to format each response to correspond with the way we store words in 
         * our libraries. That way we can properly search through them.  Specifically, this class puts extra spaces around 
         * words that are near punctuation as we do no store punctuation in out libraries.
         */

        /**
         * This method spaces out all words and separates punctuations from them
         * @param response The string that needs to be corrected
         * @return the string with a space placed between all words and punctuation
         */
        public static String space(String response) {
        	if(response.length() > 0){
                if(response.charAt(0) != ' ')
                        response = " "+response;
                if(response.charAt(response.length()-1) != ' ')
                        response = response + " ";
                response = response.replace(",", " ,");
                response = response.replace("!", " !");
                response = response.replace(".", " .");
                response = response.replace("?", " ?");
        	}
            return response;
        }
        /**
        * This method removes all punctuation from the input string
        * @param response The string that needs to be corrected
        * @return the string with all punctuation
        */
        public static String dpunc(String response) {
        	if(response.length() > 0){
                if(response.charAt(response.length()-1) != ' ')
                        response = response + " ";
                response = response.replace(",", "");
                response = response.replace("!", "");
                response = response.replace(".", "");
                response = response.replace("?", "");
        	}
            return response;
        }
}
