package CompartmentalModel.design;

import org.eclipse.emf.ecore.EObject;

/**
 * The services class used by VSM.
 * Enhanced with dynamic group detection utilities.
 */
public class Services {
    
    /**
    * See http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24 for documentation on how to write service methods.
    */
    public EObject myService(EObject self, String arg) {
       // TODO Auto-generated code
      return self;
    }
    

    public String borderColorFromPrimaryName(String key) {
        int hash = Math.abs(key.hashCode());
        int r = (hash & 0xFF0000) >> 16;
        int g = (hash & 0x00FF00) >> 8;
        int b = (hash & 0x0000FF);
//        return "rgb(" + r + "," + g + "," + b + ")";
        return "rgb(255,0,0)";
        
    }
    
    /**
     * Get color for a group value based on hash
     */
    public String getColorForGroup(String groupValue) {
        if (groupValue == null) return "black";
        
        int hash = Math.abs(groupValue.hashCode());
        String[] colors = {"blue", "orange", "purple", "green", "red", "yellow", "gray", "brown"};
        return colors[hash % colors.length];
    }
    
    /**
     * Get light version of color for group
     */
    public String getLightColorForGroup(String groupValue) {
        if (groupValue == null) return "light_gray";
        
        int hash = Math.abs(groupValue.hashCode());
        String[] colors = {"light_blue", "light_orange", "light_purple", "light_green", 
                          "light_red", "light_yellow", "light_gray", "light_chocolate"};
        return colors[hash % colors.length];
    }

}
