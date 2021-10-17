package kz.reself.impl.model;

public enum ProductStatus {
  DRAFT("DRAFT"), SENT("SENT"), ERROR("ERROR");

  private final String name;

  private ProductStatus(String name) {
    this.name = name;
  }

  public boolean equalsName(String status) {
    return name.equals(status);
  }

  public String toString() {
    return this.name;
  }
}
