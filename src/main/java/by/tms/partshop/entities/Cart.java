package by.tms.partshop.entities;

import by.tms.partshop.dto.PartDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

  private List<PartDto> cart;

  public Cart() {
    this.cart = new ArrayList<>();
  }

  public List<PartDto> getCart() {
    return cart;
  }

  public void addPartToCart(PartDto addedPart) {
    cart.add(addedPart);
  }

  public int getUserCartTotalPrice() {
    if (Optional.ofNullable(cart).isPresent()) {
      return cart.stream().mapToInt(PartDto::getPrice).sum();
    }
    return 0;
  }

  public void delUnnecessaryPart(long partCode) {
    cart.removeIf(
        part -> part.getPartCode() == partCode);
  }

  public void clear() {
    cart.clear();
  }
}
