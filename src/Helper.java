import java.util.Objects;
import java.util.Scanner;

public class Helper {
    static Scanner scan = new Scanner(System.in);
//hhhhh
    private static String loggedInUsername;
    private static UserRole loggedInUserRole;


    private static void login() throws InterruptedException {
        String username = null;
        String sifre = null;
        boolean calisti = true;

        do {
            System.out.print("Username=");
            username = scan.nextLine();
            System.out.print("Sifre=");
            sifre = scan.nextLine();
            if (username.equals("admin") && sifre.equals("Admin123")) {
                calisti=true;
                showAdminMenu();
                break;

            } else if (username.equals("user") && sifre.equals("User123")) {
                calisti=true;
                showUserMenu();
                break;

            } else {
                calisti = false;
                System.out.println("Hatali giris yaptiniz. Tekrar deneyiniz");
            }


        } while (!calisti);


        //TODO LOGIN FONKSIYONUNU YAZIN
        // login olunduğunda, "loggedInUserRole" ya ADMIN ya USER rolünü alacak, eğer null ise
        // login olunmamış demektir...

        //TODO loggedInUserRole null olup olmadığını kontrol ederek,
        //TODO kullanıcıdan, kullanıcı adı ve şifre isteyiniz...

        //TODO username:password > admin:Admin123 ya da user:User123 şartının sağlanıp sağlanmadığına bakın...
        //TODO Geçerli giriş sağlanırsa kullanıcının rolünü belirleyin,
        //TODO "Admin olarak giris yapildi!" ya da "Standart kullanici olarak giris yapildi!" uygun olan mesajı verin..
        //TODO Yoksa "Gecersiz kullanici adi veya sifre!" mesajını döndürün.

    }

    Scanner scanner = new Scanner(System.in);

    public static void anaMenu() throws InterruptedException {

      login();
        if (loggedInUserRole.equals("admin")) {
            showAdminMenu();
        } else  {
            showUserMenu();
        }

        // Kullanıcı girişi yapılıyor
        //TODO User Role'e göre kullanıcının "showAdminMenu()" ya da "showUserMenu()" ye
        //TODO Yönlendirin...

        String tercih = "";
        {//TODO Kullanıcıdan alacağınız tercihlere göre, gerekli menü metodlarına yönlendirme yapınız
            System.out.println( "\n=========== TECHNO STUDY CONFLUENCE =============\n" +
                           "=================== ANA MENU ====================\n" +
                           "\n" +
                           "\t   1- Kutuphane Bilgileri Goruntule\n" +
                           "\t   2- Uyeler Menu\n" +
                           "\t   3- Kitaplar Menu\n" +
                           "\t   Q- CIKIS\n");
           System.out.print("Lutfen Menuden tercihinizi yapiniz:"+tercih);

            tercih = scan.nextLine().toLowerCase();
            switch (tercih) {
                case "1":

                    kutuphaneBilgileriniYazdir();break;

                case "2":
                    loginAndShowUserMenu(UserRole.ADMIN);;break;

                case "3":
                    loginAndShowUserMenu(UserRole.ADMIN);   ;break;

                case "Q":
                    System.out.println("menuden cikis yaptiniz");break;
                default:

                    System.out.print("Lutfen gecerli bir tercih giriniz:");

            }



        }

        projeDurdur();
    }

    public static void kutuphaneBilgileriniYazdir() throws InterruptedException {//BU METODDA BİR DEĞİŞİKLİK YAPMANIZA GEREK YOK...

        System.out.print("Kutuphane bilgileri yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println("\n" +
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                "\t\t Kutuphane : " + Kutuphane.kutuphaneIsim +
                "\n\t\t Adres : " + Kutuphane.adres +
                "\n\t\t Telefon : " + Kutuphane.telefon);

    }

    private static void loginAndShowUserMenu(UserRole requiredRole) throws InterruptedException {
        System.out.print("Kullanici Adi: ");
        String username = scan.nextLine();
        System.out.print("Sifre: ");
        String password = scan.nextLine();

        //Kullanıcıdan aldığınız username ve password, authenticateUser(String username, String password)
        //metodunda doğrulamaya tabi tutuluyor...
        UserRole role = authenticateUser(username, password);

        if (role == null) {
            System.out.println("Gecersiz kullanici adi veya sifre.");
        } else if (role.equals("ADMIN")) {
            showAdminMenu();
        } else if (role.equals("USER")) {
            showUserMenu();
        } else {
            System.out.println("Bu sayfaya erisim izniniz yok.");
        }
        //NOT : Doğrulamadan geçerse role ADMIN veya USER olur... Geçmezse "null" olur

        //TODO null ise "Gecersiz kullanici adi veya sifre." mesajı verin
        //TODO ADMIN ise showAdminMenu() metoduyla admin menüyü gösterin
        //TODO USER ise showUserMenu() metoduyla user menüyü gösterin...
        //TODO Aksi durumlarda "Bu sayfaya erisim izniniz yok." mesajını verin...
        //if-else bloklarıyla şartlar gerçeklenebilir...
    }

    private static UserRole authenticateUser(String username, String password) {

        if (username.equals("admin") && password.equals("admin")) {
            return UserRole.ADMIN;
        } else if (username.equals("user") && password.equals("user")) {
            return UserRole.USER;
        } else {
            return null;
        }
    }

    private static void showAdminMenu() throws InterruptedException {

        String secim=null;


            System.out.println(
                    "\n=========== TECHNO STUDY CONFLUENCE ==========\n" +
                        "================== ADMIN MENU ================\n" +
                        "\n" +
                        "\t   1- Uyeler Menu\n" +
                        "\t   2- Kitaplar Menu\n" +
                        "\t   A- ANAMENU\n" +
                        "\t   Q- CIKIS");

        System.out.print("Seciminiz=");
        secim=scan.nextLine();


        switch (secim){
            case "1":
                UyeManager.uyeMenu(); break;
            case "2":
                KitapManager.kitapMenu(); break;
            case "A":
                anaMenu(); break;
            case "Q":
                projeDurdur();
            default: {
                System.out.println("Lutfen gecerli bir tercih giriniz");
                showAdminMenu();
            }

        }


        //TODO Kullanıcıdan alacağınız tercihlere göre ilgili menüye (metoda) yönlendirme yapınız...
        {
              UyeManager.uyeMenu();
                KitapManager.kitapMenu();
                anaMenu();
                projeDurdur();
                System.out.println("Lutfen gecerli bir tercih giriniz");
        }
    }

    private static void showUserMenu() throws InterruptedException {
        String tercih = "";
        //TODO Kullanıcı Çıkış Yapmadığı Sürece User Menüde Kalsın...
        do{
            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE ===========\n" +
                            "================== USER MENU =================\n" +
                            "\n" +
                            "\t   1- Uyeleri Listele\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Kitaplari Listele\n" +
                            "\t   5- Yazardan Kitap Bulma\n" +
                            "\t   6- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   7- Kitap Odunc Al\n" +
                            "\t   8- Kitap Iade Et\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");

            //TODO Kullanıcıdan alacağınız tercihlere göre ilgili menüye (metoda) yönlendirme yapınız...

            System.out.print("User Menüden bir tercih yapiniz: ");
            tercih=scan.nextLine().toLowerCase();

            switch (tercih){
                case "1":
                    UyeManager.uyeListesiYazdir();
                    break;
                case "2":
                    UyeManager.soyisimdenUyeBulma();
                    break;
                case "3":
                    UyeManager.sehreGoreUyeBulma();
                    break;
                case "4":
                    KitapManager.kitapListesiYazdir();
                    break;
                case "5":
                    KitapManager.yazardanKitapBulma();
                    break;
                case "6":
                    KitapManager.turVeyaYayinTarihiIleKitapBulma();
                    break;
                case "7":
                    KitapManager.kitapOduncAl();
                    break;
                case "8":
                    KitapManager.kitapIadeEt();
                    break;
                case "A":
                    anaMenu();
                    break;
                case "Q":
                    projeDurdur();
                    break;
                default:
                    System.out.println("Lutfen gecerli bir tercih giriniz");
            }
        }while (!tercih.equalsIgnoreCase("q"));
    }

    public static void projeDurdur() {
        System.out.println("Kutuphane projesinden ciktiniz");
        System.exit(0);
    }
}

