import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KitapManager extends Veritabani {

    static Scanner scan = new Scanner(System.in);
    static Scanner scanner = new Scanner(System.in);

    public static void kitapMenu() throws InterruptedException {
        String tercih = "";

        while (!tercih.equalsIgnoreCase("Q")) {// TODO Kullanıcı Çıkış Yapmadığı sürece menüde kalalım...
            System.out.println(
                    "\n============ TECHNO STUDY BOOTCAMP ============\n" +
                            "================== KITAP MENU =================\n" +
                            "\t   1- Kitap Listesi Yazdir\n" +
                            "\t   2- Yazardan Kitap Bulma\n" +
                            "\t   3- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   4- Bilgilerini Girerek Kitap Ekleme\n" +
                            "\t   5- Kitap Ismi Ile Kayit Silme \t\n" +
                            "\t   6- Kitap Odunc Al \t\n" +
                            "\t   7- Kitap Iade Et \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");

            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...

            System.out.print("Tercihinizi yapiniz: ");
            tercih = scan.nextLine();

            switch (tercih.toLowerCase()) {
                case "1":
                    kitapListesiYazdir();
                    break;
                case "2":
                    yazardanKitapBulma();
                    break;
                case "3":
                    turVeyaYayinTarihiIleKitapBulma();
                    break;
                case "4":
                    kitapEkle();
                    break;
                case "5":
                    isimIleKitapSilme();
                    break;
                case "6":
                    kitapOduncAl();
                    break;
                case "7":
                    kitapIadeEt();
                    break;
                case "a":
                    Helper.anaMenu();
                    break;
                case "q":
                    System.out.println("Programdan cikis yapiliyor...");
                    break;
                default:
                    System.out.println("Gecerli bir tercih giriniz!");

            }


        }
        Helper.projeDurdur();

    }

    public static void kitapOduncAl() {
        Scanner oku = new Scanner(System.in);
        System.out.println("Odunc almak istediginiz kitabin ismini giriniz: ");
        String kitapIsmi = oku.nextLine();

        if (oduncAlinanKitaplarMap.containsKey(kitapIsmi)) {

            System.out.println("Bu kitap zaten odunc alinmis!");

        } else if (kitaplarMap.containsKey(kitapIsmi)) {
            String kitapBilgisi = kitaplarMap.get(kitapIsmi);
            oduncAlinanKitaplarMap.put(kitapIsmi, kitapBilgisi);
            kitaplarMap.remove(kitapIsmi);
            System.out.println(kitapIsmi + " adli kitap odunc alindi.");

        } else {
            System.out.println("Kitap bulunamadi. Lutfen gecerli bir kitap ismi giriniz.");
        }
    }


    //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
    //TODO kitap ödünç alma metodunu tamamlayın...
    //NOT: Veritabanı'nda ödünç almayla alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
    //Ödünç alındığında kitaplarMap 'ten düşüp bu map e eklenecek...


    public static void kitapIadeEt() {
        Scanner oku = new Scanner(System.in);
        System.out.println("Iade etmek istediginiz kitabin ismini giriniz: ");
        String kitapIsmi = oku.nextLine();

        if (iadeEdilenKitaplarMap.containsKey(kitapIsmi)) {

            System.out.println("Bu kitap zaten iade edildi");
        } else if (oduncAlinanKitaplarMap.containsKey(kitapIsmi)) {
            String kitapBilgisi = oduncAlinanKitaplarMap.get(kitapIsmi);

            iadeEdilenKitaplarMap.put(kitapIsmi, kitapBilgisi);

            oduncAlinanKitaplarMap.remove(kitapIsmi);
            System.out.println(kitapIsmi + " kitabi basariyla iade edildi.");
        } else {
            System.out.println("Bu kitap odunc alınmamış veya mevcut değil. Lutfen gecerli bir kitap ismi giriniz.");

            //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
            //TODO kitap iade etme metodunu tamamlayın...
            //NOT: Veritabanı'nda iade etmeyle alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
            //Kitap iade edildiğinde,  kitaplarMap 'e geri eklenmeli...

        }
    }

    //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
    //TODO kitap iade etme metodunu tamamlayın...
    //NOT: Veritabanı'nda iade etmeyle alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
    //Kitap iade edildiğinde,  kitaplarMap 'e geri eklenmeli...


    private static void isimIleKitapSilme() throws InterruptedException {//İPUCU METODU... Bu metodu değiştirmenize gerek yok.
        System.out.println("Silinecek kitabin ismini giriniz");
        String silinecekKitap = scan.nextLine();

        String silinecekValue = kitaplarMap.get(silinecekKitap);
        String sonucValue = kitaplarMap.remove(silinecekKitap);

        System.out.print(silinecekKitap + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        //ARTIK UYGUN YERLERDE BEKLEMEDİĞİNİZ SONUÇLAR İÇİN TRY CATCH KULLANABİLİRSİNİZ...
        //////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("Istediginiz kitap ismi bulunamadi");
        }/////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private static void kitapEkle() {

        Scanner oku = new Scanner(System.in);
        System.out.print("Kitap Adini giriniz=");

        String kitapAdi = oku.nextLine();

        System.out.print("Yazarin Adini giriniz=");
        String yazarAdi = oku.nextLine();

        KitapTuru kitapTuru = null;
        boolean turGecersiz = true;

        while (turGecersiz) {
            System.out.println("Kitap Turunu Giriniz (Tarih, Polisiye, Kurgu, Roman, Destan): ");
            String tur = oku.nextLine();

            try {
                kitapTuru = KitapTuru.valueOf(tur.toUpperCase());
                turGecersiz = false;

            } catch (IllegalArgumentException e) {
                System.out.println("Hatali giris! Lütfen kitap türünü tekrar giriniz");
            }
        }
        System.out.print("Yayinlanma Yilini giriniz: ");
        int yayinYili = oku.nextInt();
        oku.nextLine();

        String kitapBilgisi = yazarAdi + ", " + kitapTuru + ", " + yayinYili;

        kitaplarMap.put(kitapAdi, kitapBilgisi);

        System.out.println("Kitap ekleme islemi tamamlandi.");


        //"A Tale of Two Cities", "Charles Dickens, Tarih, 1859" >> Kitap key,value su buna benzer şekilde...

        //TODO Kitap Adını, Yazar Adını, Kitap Türünü ve Yayınlanma Yılını Kullanıcıdan alarak,
        //TODO kitaplarMap'e ekleme yapınız...

        //TODO MÜMKÜNSE, kitap türünü, Enum olarak tanımlanan KitapTuru sınıfını kullanarak alınız...
        //KİTAP TÜRLERİ >>     TARIH, POLISIYE, KURGU, ROMAN, DESTAN, TANIMLANMAMIS_TUR
        //TODO Kullanıcı kitap türünü yanlış girdiği sürece , kullanıcıya
        //TODO "Hatali giris! Lutfen kitap turunu tekrar giriniz: " mesajı verin...
        //TODO while ve try-catch kullanılabilir... Giriş başarılı olursa try-catch bloğu kırılarak konsoldan
        //TODO yayınlama yılı alıp kitap ekleme işlemine devam edilebilir...
        //Kullanıcı tarafından girilen stringi, KitapTuru sınıfında tanımlanan türlerden birine çevirmeniz gerkecek...
        // kitapTuru değişkeni artık geçerli bir değere sahipse...
        // Diğer işlemlere devam edebilirsiniz.
        //System.out.println("Yayinlanma Tarihi: ");
        //String yayinTarihi = scan.nextLine();

        //TODO Ekleme işlemini tamamlayın...


    }

    public static void turVeyaYayinTarihiIleKitapBulma() throws InterruptedException {

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...

        Scanner oku = new Scanner(System.in);
        System.out.println("Aradiginiz kitabin türünü veya yayin yilini giriniz: ");
        String aranan = oku.nextLine();

        Set<Map.Entry<String, String>> entrySet = kitaplarMap.entrySet();
        boolean bulunanKitap = false;

        System.out.println("(Tarih, Polisiye, Kurgu, Roman, Destan)");
        //TODO Metodu kullanıcıdan alacağınız girdileri kullanarak tamamlayın...

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +

                        "Kitap Ismi     :   Yazar Ismi , Kitap Turu , Yayin Yili");
        for (Map.Entry<String, String> entry : entrySet) {
            String kitapAdi = entry.getKey();
            String yazarBilgisi = entry.getValue();
            String[] kitapBilgileri = yazarBilgisi.split(", ");
            String kitapTuru = kitapBilgileri[1];
            String yayinYili = kitapBilgileri[2];

            if (kitapTuru.equalsIgnoreCase(aranan) || yayinYili.equals(aranan)) {

                System.out.println(kitapAdi + " : " + yazarBilgisi);
                bulunanKitap = true;
            }

        }
        if (!bulunanKitap) {
            System.out.println("Girilen kriterlere uygun kitap bulunamadi");
        }

    }


    public static void yazardanKitapBulma() throws InterruptedException {

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        Scanner oku = new Scanner(System.in);
        System.out.println("Istediginiz yazar ismini yaziniz: ");
        String arananYazar = oku.nextLine();
        boolean bulunanKitap = false;


        for (Map.Entry<String, String> entry : kitaplarMap.entrySet()) {
            String kitapAdi = entry.getKey();
            String yazarBilgisi = entry.getValue();
            String[] yazarBilgileri = yazarBilgisi.split(", ");
            String yazarIsmi = yazarBilgileri[0];


            if (yazarIsmi.equalsIgnoreCase(arananYazar)) {
                String KitapTuru = null;
                System.out.println(kitapAdi + " : " + yazarBilgisi);
                bulunanKitap = true;
            }

        }
        if (!bulunanKitap) {
            System.out.println("Girilen yazarin kitabi bulunamadi");
        }
        //TODO Metodu kullanıcıdan alacağınız girdileri kullanarak tamamlayın...

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");
        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir.
        // Şart değil, isteğe bağlı.

    }

    public static void kitapListesiYazdir() throws InterruptedException { //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        Set<Map.Entry<String, String>> kitaplarEntrySet = kitaplarMap.entrySet();

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

        System.out.print("Kitap Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println();


        for (Map.Entry<String, String> each : kitaplarEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();
            System.out.println(eachKey + " : " + eachValue + " | ");
        }


        //TODO Kitapları listeleyecek metodu oluşturun...
        //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...
    }
}
