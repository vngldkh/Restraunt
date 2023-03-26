package visitor;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record Visitor(String name, LocalDateTime started, LocalDateTime ended, int total, ArrayList<VisOrdDish> dishes) {

}
