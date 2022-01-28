
/**
 *
 * @author mert_
 * This class is defined for Orders.
 * The main purpose of this class is to collect details of Orders.
 *
 **/

public class Order {

    private String details;
    private String description;

    public Order() {
        this.details="None";
        this.description="None";
    }
    public Order(String details, String description) {
        String cost;
        details=details.toUpperCase();
        switch (details) {
            case "BİG":
                this.details="One Big Pizza Costs 15$ ";
                break;
            case "MEDIUM":
                this.details="One Medium Pizza Costs 10$ ";
                break;
            case "SMALL":
                this.details="One Small Pizza Costs 5$ ";
                break;
            default:
                this.details="Have not ordered any pizza ";
                break;
        }
        this.description=description;
        //   System.out.println(this.details+this.description);

    }
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}












