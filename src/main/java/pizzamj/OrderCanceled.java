package pizzamj;

public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long pizzaId;
    private String orderStatus;
    private Long qty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}