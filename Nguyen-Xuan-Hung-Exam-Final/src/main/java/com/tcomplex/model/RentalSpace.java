package com.tcomplex.model;

import java.sql.Date;

public class RentalSpace {
    private String spaceId;
    private double area;
    private String status;
    private int floor;
    private String type;
    private String description;
    private long price;
    private Date startDate;
    private Date endDate;

    public RentalSpace() {}

    public RentalSpace(String spaceId, double area, String status, int floor, String type,
                       String description, long price, Date startDate, Date endDate) {
        this.spaceId = spaceId;
        this.area = area;
        this.status = status;
        this.floor = floor;
        this.type = type;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getSpaceId() { return spaceId; }
    public void setSpaceId(String spaceId) { this.spaceId = spaceId; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
