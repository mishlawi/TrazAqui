package Model;

import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class EncomendaMedica extends Encomenda {

    public EncomendaMedica() {
        super();
    }

    public EncomendaMedica(String comprador, String distribuidor, String loja, Point2D.Double moradaOrigem, Point2D.Double moradaDestino, String referencia, float peso, LocalDateTime date, Duration tempo, List<Produto> lst, double custo) {
        super(comprador, distribuidor, loja, moradaOrigem, moradaDestino, referencia, peso, date, tempo, lst, custo);
    }

    public EncomendaMedica(Encomenda a) {
        super(a);
    }
}
