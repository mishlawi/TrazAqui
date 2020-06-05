package Model;

import java.util.Comparator;

public class ComparatorTaxa implements Comparator<EmpresaTransportadora>
{
    public int compare(EmpresaTransportadora a1, EmpresaTransportadora a2)
    {
        if (a1.getTaxa() < a2.getTaxa()) return 1;
        if (a1.getTaxa() > a2.getTaxa()) return -1;
        return 0;
    }
}
