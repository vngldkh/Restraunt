package visitor;

import java.util.Date;

public record Visitor(String name, Date started, Date ended, int total, VisOrdDish[] dishes) {

}
