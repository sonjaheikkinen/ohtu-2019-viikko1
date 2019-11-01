package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varastoNeg;
    Varasto varastoKuorm;
    Varasto varastoKuormNeg;
    Varasto varastoKuormIsoSaldo;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoNeg = new Varasto(-10);
        varastoKuorm = new Varasto(10, 5);
        varastoKuormNeg = new Varasto(-10, -5);
        varastoKuormIsoSaldo = new Varasto (10, 15);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonTilavuusNollaJosKonstruktorilleAnnettuNegatiivinenTilavuus() {
        assertEquals(0, varastoNeg.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormittavaKonstruktoriLuoVarastonOikeallaTilavuudella() {
        assertEquals(10, varastoKuorm.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormittavaKonstruktoriAsettaaTilavuudeksiNollaJosAnnettuNegatiivinenTilavuus() {
        assertEquals(0, varastoKuormNeg.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormittavaKonstruktoriAsettaaSaldoksiNollaJosAnnettuNegatiivinenSaldo() {
        assertEquals(0, varastoKuormNeg.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormittavaKonstruktoriAsettaaSaldonOikeinKunAlkuSaldoPienempiKuinTilavuus() {
        assertEquals(5, varastoKuorm.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormittavaKonstruktoriAsettaaSaldonOikeinKunAlkuSaldoSuurempiKuinTilavuus() {
        assertEquals(10, varastoKuormIsoSaldo.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiTeeMitaanJosMaaraNegatiivinen() {
        varastoKuorm.lisaaVarastoon(-2);
        assertEquals(5, varastoKuorm.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysAsettaaSaldoksiTilavuudenJosMaaraSuurempiKuinTilavuus() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaEiTeeMitäänJosMaaraNegatiivinen() {
        varastoKuorm.otaVarastosta(-2);
        assertEquals(5, varastoKuorm.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaTyhjentaaVarastonJosMaaraSuurempiKuinSaldo() {
        varastoKuorm.otaVarastosta(10);
        assertEquals(0, varastoKuorm.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoTulostuuOikein() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }

}
