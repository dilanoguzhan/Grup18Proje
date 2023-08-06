import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UyeManager extends Veritabani {
    static Scanner scan = new Scanner(System.in);
    static Scanner scanner = new Scanner(System.in);

    public static void uyeMenu() throws InterruptedException {

        String tercih = "";


        //TODO Kullanıcı Çıkış Yapmadığı Sürece, Menüyü Görmeye Devam Etsin...




            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                            "================= UYE MENU =================\n" +
                            "\n" +
                            "\t   1- Uye Listesi Yazdir\t\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Bilgilerini Girerek Uye Ekleme\n" +
                            "\t   5- Kimlik No Ile Kayit Silme \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS\n\n");
        do {
            System.out.print("Seciminiz=");
            String secim = scan.nextLine().toUpperCase();

            switch (secim.toUpperCase()) {
                case "1":
                    uyeListesiYazdir();
                    break;
                case "2":
                    soyisimdenUyeBulma();
                    break;
                case "3":
                    sehreGoreUyeBulma();
                    break;
                case "4":
                    uyeEkleme();
                    break;
                case "5":
                    tcNoIleUyeSil();
                    break;
                case "A":
                    Helper.anaMenu();
                    break;
                case "Q":
                    Helper.projeDurdur();
                    break;
                default: {
                    System.out.println("Lutfen gecerli tercih yapiniz: ");
                }


            }

            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...

            // Uye Listesi Yazdir
            //uyeListesiYazdir();
            // Soyisimden Uye Bulma
            //soyisimdenUyeBulma();
            // Sehre Gore Uye Bulma
            //sehreGoreUyeBulma();
            // Bilgilerini Girerek Uye Ekleme
            //uyeEkleme();
            // Kimlik No Ile Kayit Silme
            //tcNoIleUyeSil();
            //Helper.anaMenu();
            //System.out.println("Lutfen gecerli tercih yapiniz: ");


        } while (!tercih.equalsIgnoreCase("q"));

    }

    public static void tcNoIleUyeSil() throws InterruptedException {

        System.out.print("Silinecek uyeye ait kimlik no giriniz:");
        String tcNO = scan.nextLine();
        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        String silinecekValue = uyelerMap.get(tcNO);
        String sonucValue = uyelerMap.remove(tcNO);

        System.out.print(tcNO + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }
        System.out.println("\n" + tcNO + " nolu uye basariyla silindi");

        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("Bu kimlik numarasindan uye bulunamadi");
        }


        //TODO Kullanıcıdan alacağınız kimlik no ile ilgili üyeyi kayıtlardan siliniz...
        //System.out.println("Silinecek uyeye ait kimlik no giriniz: ");

        //TODO Gerekli atamaları yapınız. Aşağıdaki try-catch bloğu yardımcı olabilir...


        //try {
        //    boolean sonuc = sonucValue.equals(silinecekValue);
        //} catch (Exception e) {
        //    System.out.println("Istediginiz Tc numarasi ile uye bulunamadi.");
        //}
    }

    public static void uyeEkleme() {

        System.out.print("TC NO=");
        String TCNO = scanner.nextLine();
        System.out.print("Isim=");
        String isim = scan.nextLine();
        System.out.print("Soyisim=");
        String soyisim = scan.nextLine();
        System.out.print("Sehir=");
        String sehir = scan.nextLine();
        System.out.print("Dogum yili=");
        int dogYil = scanner.nextInt();

        String bilgiler = (isim + ", " + soyisim + ", " + sehir + ", " + dogYil);

        Veritabani.uyelerMap.put(TCNO, bilgiler);
        System.out.println(TCNO + " nolu uye basariyla eklendi.");

        //TODO Kullanıcıdan Tc no , Isim, Soyisim, Sehir, Dogum Yili alınız...
        //TODO Aldığınız değeri UyelerMap mapine uygun şekilde ekleyiniz...

    }

    public static void sehreGoreUyeBulma() throws InterruptedException {

        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde şehir araması yapın;
        //TODO Girilen şehire sahip tüm üyeleri map veya liste olarak döndürünüz...
        System.out.println("Aradiginiz Uyenin Sehrini Giriniz:");
        String sehir=scan.nextLine();
        System.out.println(
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                        "=============== SEHIR ILE UYE ARAMA ===============\n" +
                        "TcNo : Isim , Soyisim , Sehir, D.Yili");

        int bulunanSayac = 0;
        for (Map.Entry<String, String> entry : uyelerMap.entrySet()) {
            String tcNo = entry.getKey();
            String uyeBilgileri = entry.getValue();
            String[] bilgiler = uyeBilgileri.split(", ");
            String uyeSehir = bilgiler[2];

            if (uyeSehir.toLowerCase().contains(sehir.toLowerCase())) {
                System.out.println(tcNo + " : " + uyeBilgileri);
                bulunanSayac++;
            }
        }

        if (bulunanSayac == 0) {
            System.out.println("Aradığınız soyisime sahip üye bulunamadı.");
        }


        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir... Zorunlu değil...

    }

    public static void soyisimdenUyeBulma() throws InterruptedException {
        System.out.print("Aradığınız üyenin soyisminin tamamını ya da bir kısmını giriniz: ");
        String soyisim = scan.nextLine();

        System.out.println(
                "\n========== TECHNO STUDY BOOTCAMP ===========\n" +
                        "=========== SOYISIM ILE UYE ARAMA ==========\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        int bulunanSayac = 0;
        for (Map.Entry<String, String> entry : uyelerMap.entrySet()) {
            String tcNo = entry.getKey();
            String uyeBilgileri = entry.getValue();
            String[] bilgiler = uyeBilgileri.split(" ");
            String uyeSoyisim = bilgiler[1];

            if (uyeSoyisim.toLowerCase().contains(soyisim.toLowerCase())) {
                System.out.println(tcNo + " : " + uyeBilgileri);
                bulunanSayac++;
            }
        }

        if (bulunanSayac == 0) {
            System.out.println("Aradığınız soyisime sahip üye bulunamadı.");
        }


        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir. Şart değil, isteğe bağlı.

        //TODO Syisminin bir kısmı girilse bile bulunan üyeler listelensin...
    }

    public static void uyeListesiYazdir() throws InterruptedException {
        //İPUCU METODU: Bu metodu değiştirmenize gerek yok...

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.print("Uye Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                        "=============== UYE LISTESI ================\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir...
        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
    }
}
