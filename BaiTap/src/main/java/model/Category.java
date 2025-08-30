package model;

public class Category {
    private int cateid;
    private String catename;
    private String icon;
    
    // Default constructor
    public Category() {
    }
    
    // Constructor with all fields
    public Category(int cateid, String catename, String icon) {
        this.cateid = cateid;
        this.catename = catename;
        this.icon = icon;
    }
    
    // Constructor without ID (for insertion)
    public Category(String catename, String icon) {
        this.catename = catename;
        this.icon = icon;
    }
    
    // Getters and setters
    public int getCateid() {
        return cateid;
    }
    
    public void setCateid(int cateid) {
        this.cateid = cateid;
    }
    
    public String getCatename() {
        return catename;
    }
    
    public void setCatename(String catename) {
        this.catename = catename;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    @Override
    public String toString() {
        return "Category{" +
                "cateid=" + cateid +
                ", catename='" + catename + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}