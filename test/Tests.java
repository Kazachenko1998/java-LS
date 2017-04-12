import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import static LS.ls.commandLine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import LS.FlagArg;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

class Tests {

    private static FileWriter setSize(FileWriter writer, long size) throws IOException {

        for (long i=0; i<size; i++){
            writer.write('a');
        }
        return writer;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @BeforeAll
    static void createdFiles() throws IOException {
        File destFolder = new File("testIn");

        destFolder.mkdir();
        FileWriter writer = new FileWriter("Ace_Stream_Media_3.1.2.exe1");
        writer =setSize(writer,79522432);
        writer.close();
        File file = new File("Ace_Stream_Media_3.1.2.exe1");
        file.setLastModified(1458311585083L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("AdbeRdr930_ru_RU.exe1");
        writer =setSize(writer,25879608);
        writer.close();
        file = new File("AdbeRdr930_ru_RU.exe1");
        file.setLastModified(1279732176000L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip1");
        writer =setSize(writer,81475851);
        writer.close();
        file = new File("atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip1");
        file.setLastModified(1438626227247L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("avast_free_antivirus_setup_online_comss.exe1");
        writer =setSize(writer,5481336);
        writer.close();
        file = new File("avast_free_antivirus_setup_online_comss.exe1");
        file.setLastModified(1438876146121L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe1");
        writer =setSize(writer,65762043);
        writer.close();
        file = new File("Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe1");
        file.setLastModified(1273572330000L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("DTLiteInstaller.exe1");
        writer =setSize(writer,1709792);
        writer.close();
        file = new File("DTLiteInstaller.exe1");
        file.setLastModified(1438952882607L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("Git-2.10.0-64-bit.exe1");
        writer =setSize(writer,33049944);
        writer.close();
        file = new File("Git-2.10.0-64-bit.exe1");
        file.setLastModified(1473356327247L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("K-Lite_Codec_Pack_960_Mega.exe1");
        writer =setSize(writer,24222983);
        writer.close();
        file = new File("K-Lite_Codec_Pack_960_Mega.exe1");
        file.setLastModified(1355557062417L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("KMPlayer 2.9.4.1434 Final.exe1");
        writer =setSize(writer,29391072);
        writer.close();
        file = new File("KMPlayer 2.9.4.1434 Final.exe1");
        file.setLastModified(1338740319172L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("mseinstall.exe1");
        writer =setSize(writer,13697208);
        writer.close();
        file = new File("mseinstall.exe1");
        file.setLastModified(1386123358172L);
        file.renameTo(new File(destFolder, file.getName()));

        writer = new FileWriter("online-consultant.exe1");
        writer =setSize(writer,15745976);
        writer.close();
        file = new File("online-consultant.exe1");
        file.setLastModified(1450679908864L);
        file.renameTo(new File(destFolder, file.getName()));

        file = new File("TeamViewerPortable1");
        file.mkdir();
        file.setLastModified(1490861124666L);
        file.renameTo(new File(destFolder, file.getName()));

        file = new File("teeworlds-0.5.1-win321");
        file.mkdir();
        file.setLastModified(1490861124716L);
        file.renameTo(new File(destFolder, file.getName()));

        file = new File("Tor Browser1");
        file.mkdir();
        file.setLastModified(1490861151914L);
        file.renameTo(new File(destFolder, file.getName()));

        file = new File("тема1");
        file.mkdir();
        file.setLastModified(1490861167273L);
        file.renameTo(new File(destFolder, file.getName()));

    }

    @AfterAll
    static void deletedFiles() {
        File file = new File("testIn");
    delete(file);
    delete(new File("outPut"));//слишком часто он остается...
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
    private static void delete(File file) {
        if(!file.exists())
            return;
        if(file.isDirectory())
        {
            for(File f : file.listFiles())
                delete(f);
            file.delete();
        }
        else
        {
            file.delete();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void assertFileContent(String[] name, String expectedContent) throws IOException {
        commandLine(name);
        String out = new FlagArg(name).getOutput();
        StringBuilder content = new StringBuilder();
        try (FileReader reader = new FileReader(out)) {
            int c;
            while ((c = reader.read()) != -1) {
                content.append((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(expectedContent, content.toString());
        (new File(out)).delete();
    }

    @Test
    @Tag("---d")
    void __od() throws IOException {
        assertFileContent(" -o outPut testIn".split(" "), "Ace_Stream_Media_3.1.2.exe1\n" +
                "AdbeRdr930_ru_RU.exe1\n" +
                "atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip1\n" +
                "avast_free_antivirus_setup_online_comss.exe1\n" +
                "Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe1\n" +
                "DTLiteInstaller.exe1\n" +
                "Git-2.10.0-64-bit.exe1\n" +
                "K-Lite_Codec_Pack_960_Mega.exe1\n" +
                "KMPlayer 2.9.4.1434 Final.exe1\n" +
                "mseinstall.exe1\n" +
                "online-consultant.exe1\n" +
                "TeamViewerPortable1\n" +
                "teeworlds-0.5.1-win321\n" +
                "Tor Browser1\n" +
                "тема1\n");
    }

    @Test
    @Tag("l--f")
    void __of() throws IOException {
        assertFileContent(" -o outPut testIn\\Ace_Stream_Media_3.1.2.exe1".split(" "), "Ace_Stream_Media_3.1.2.exe1\n");
    }

    @Test
    @Tag("lh-f")
    void lhof() throws IOException {
        assertFileContent("-l -h -o outPut testIn\\Ace_Stream_Media_3.1.2.exe1".split(" "), "Ace_Stream_Media_3.1.2.exe1  xrw   последнее изменение 18.03.2016 05:33:05   75MB\n");

    }

    @Test
    @Tag("---d")
    void l_of() throws IOException {
        assertFileContent("-l -o outPut testIn\\Ace_Stream_Media_3.1.2.exe1".split(" "), "testIn\\Ace_Stream_Media_3.1.2.exe1  111 1458311585083 79522432\n");
    }

    @Test
    @Tag("l--d")
    void l_od() throws IOException {
        assertFileContent("-l -o outPut testIn".split(" "), "testIn\\Ace_Stream_Media_3.1.2.exe1                                111 1458311585083 79522432\n" +
                "testIn\\AdbeRdr930_ru_RU.exe1                                      111 1279732176000 25879608\n" +
                "testIn\\atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip1        111 1438626227247 81475851\n" +
                "testIn\\avast_free_antivirus_setup_online_comss.exe1               111 1438876146121 5481336\n" +
                "testIn\\Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe1  111 1273572330000 65762043\n" +
                "testIn\\DTLiteInstaller.exe1                                       111 1438952882607 1709792\n" +
                "testIn\\Git-2.10.0-64-bit.exe1                                     111 1473356327247 33049944\n" +
                "testIn\\K-Lite_Codec_Pack_960_Mega.exe1                            111 1355557062417 24222983\n" +
                "testIn\\KMPlayer 2.9.4.1434 Final.exe1                             111 1338740319172 29391072\n" +
                "testIn\\mseinstall.exe1                                            111 1386123358172 13697208\n" +
                "testIn\\online-consultant.exe1                                     111 1450679908864 15745976\n" +
                "testIn\\TeamViewerPortable1                                        111 1490861124666 0\n" +
                "testIn\\teeworlds-0.5.1-win321                                     111 1490861124716 0\n" +
                "testIn\\Tor Browser1                                               111 1490861151914 0\n" +
                "testIn\\тема1                                                      111 1490861167273 0\n");
    }

    @Test
    @Tag("lh-d")
    void lhod() throws IOException {
        assertFileContent("-l -h -o outPut testIn".split(" "), "Ace_Stream_Media_3.1.2.exe1                                xrw   последнее изменение 18.03.2016 05:33:05   75MB\n" +
                "AdbeRdr930_ru_RU.exe1                                      xrw   последнее изменение 21.07.2010 09:09:36   24MB\n" +
                "atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip1        xrw   последнее изменение 03.08.2015 09:23:47   77MB\n" +
                "avast_free_antivirus_setup_online_comss.exe1               xrw   последнее изменение 06.08.2015 06:49:06   5MB\n" +
                "Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe1  xrw   последнее изменение 11.05.2010 02:05:30   62MB\n" +
                "DTLiteInstaller.exe1                                       xrw   последнее изменение 07.08.2015 04:08:02   1MB\n" +
                "Git-2.10.0-64-bit.exe1                                     xrw   последнее изменение 08.09.2016 08:38:47   31MB\n" +
                "K-Lite_Codec_Pack_960_Mega.exe1                            xrw   последнее изменение 15.12.2012 11:37:42   23MB\n" +
                "KMPlayer 2.9.4.1434 Final.exe1                             xrw   последнее изменение 03.06.2012 08:18:39   28MB\n" +
                "mseinstall.exe1                                            xrw   последнее изменение 04.12.2013 06:15:58   13MB\n" +
                "online-consultant.exe1                                     xrw   последнее изменение 21.12.2015 09:38:28   15MB\n" +
                "TeamViewerPortable1                                        xrw   последнее изменение 30.03.2017 11:05:24   (Папка) 0Byte\n" +
                "teeworlds-0.5.1-win321                                     xrw   последнее изменение 30.03.2017 11:05:24   (Папка) 0Byte\n" +
                "Tor Browser1                                               xrw   последнее изменение 30.03.2017 11:05:51   (Папка) 0Byte\n" +
                "тема1                                                      xrw   последнее изменение 30.03.2017 11:06:07   (Папка) 0Byte\n");
    }

    @Test
    @Tag("--rd")
    void __odr() throws IOException {
        assertFileContent("-r -o outPut testIn".split(" "), "тема1\n" +
                "Tor Browser1\n" +
                "teeworlds-0.5.1-win321\n" +
                "TeamViewerPortable1\n" +
                "online-consultant.exe1\n" +
                "mseinstall.exe1\n" +
                "KMPlayer 2.9.4.1434 Final.exe1\n" +
                "K-Lite_Codec_Pack_960_Mega.exe1\n" +
                "Git-2.10.0-64-bit.exe1\n" +
                "DTLiteInstaller.exe1\n" +
                "Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe1\n" +
                "avast_free_antivirus_setup_online_comss.exe1\n" +
                "atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip1\n" +
                "AdbeRdr930_ru_RU.exe1\n" +
                "Ace_Stream_Media_3.1.2.exe1\n");
    }

    @Test
    @Tag("l-rf")
    void __ofr() throws IOException {
        assertFileContent("-r -o outPut testIn\\Ace_Stream_Media_3.1.2.exe1".split(" "), "Ace_Stream_Media_3.1.2.exe1\n");
    }

    @Test
    @Tag("lhrf")
    void lhofr() throws IOException {
        assertFileContent("-l -h -r -o outPut testIn\\Ace_Stream_Media_3.1.2.exe1".split(" "), "Ace_Stream_Media_3.1.2.exe1  xrw   последнее изменение 18.03.2016 05:33:05   75MB\n");

    }

    @Test
    @Tag("--rf")
    void l_ofr() throws IOException {
        assertFileContent("-l -r -o outPut testIn\\Ace_Stream_Media_3.1.2.exe1".split(" "), "testIn\\Ace_Stream_Media_3.1.2.exe1  111 1458311585083 79522432\n");
    }

    @Test
    @Tag("l-rd")
    void l_odr() throws IOException {
        assertFileContent("-l -r -o outPut testIn".split(" "), "testIn\\тема1                                                      111 1490861167273 0\n" +
                "testIn\\Tor Browser1                                               111 1490861151914 0\n" +
                "testIn\\teeworlds-0.5.1-win321                                     111 1490861124716 0\n" +
                "testIn\\TeamViewerPortable1                                        111 1490861124666 0\n" +
                "testIn\\online-consultant.exe1                                     111 1450679908864 15745976\n" +
                "testIn\\mseinstall.exe1                                            111 1386123358172 13697208\n" +
                "testIn\\KMPlayer 2.9.4.1434 Final.exe1                             111 1338740319172 29391072\n" +
                "testIn\\K-Lite_Codec_Pack_960_Mega.exe1                            111 1355557062417 24222983\n" +
                "testIn\\Git-2.10.0-64-bit.exe1                                     111 1473356327247 33049944\n" +
                "testIn\\DTLiteInstaller.exe1                                       111 1438952882607 1709792\n" +
                "testIn\\Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe1  111 1273572330000 65762043\n" +
                "testIn\\avast_free_antivirus_setup_online_comss.exe1               111 1438876146121 5481336\n" +
                "testIn\\atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip1        111 1438626227247 81475851\n" +
                "testIn\\AdbeRdr930_ru_RU.exe1                                      111 1279732176000 25879608\n" +
                "testIn\\Ace_Stream_Media_3.1.2.exe1                                111 1458311585083 79522432\n");
    }

    @Test
    @Tag("lhrd")
    void lhodr() throws IOException {
        assertFileContent("-l -h -r -o outPut testIn".split(" "), "тема1                                                      xrw   последнее изменение 30.03.2017 11:06:07   (Папка) 0Byte\n" +
                "Tor Browser1                                               xrw   последнее изменение 30.03.2017 11:05:51   (Папка) 0Byte\n" +
                "teeworlds-0.5.1-win321                                     xrw   последнее изменение 30.03.2017 11:05:24   (Папка) 0Byte\n" +
                "TeamViewerPortable1                                        xrw   последнее изменение 30.03.2017 11:05:24   (Папка) 0Byte\n" +
                "online-consultant.exe1                                     xrw   последнее изменение 21.12.2015 09:38:28   15MB\n" +
                "mseinstall.exe1                                            xrw   последнее изменение 04.12.2013 06:15:58   13MB\n" +
                "KMPlayer 2.9.4.1434 Final.exe1                             xrw   последнее изменение 03.06.2012 08:18:39   28MB\n" +
                "K-Lite_Codec_Pack_960_Mega.exe1                            xrw   последнее изменение 15.12.2012 11:37:42   23MB\n" +
                "Git-2.10.0-64-bit.exe1                                     xrw   последнее изменение 08.09.2016 08:38:47   31MB\n" +
                "DTLiteInstaller.exe1                                       xrw   последнее изменение 07.08.2015 04:08:02   1MB\n" +
                "Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe1  xrw   последнее изменение 11.05.2010 02:05:30   62MB\n" +
                "avast_free_antivirus_setup_online_comss.exe1               xrw   последнее изменение 06.08.2015 06:49:06   5MB\n" +
                "atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip1        xrw   последнее изменение 03.08.2015 09:23:47   77MB\n" +
                "AdbeRdr930_ru_RU.exe1                                      xrw   последнее изменение 21.07.2010 09:09:36   24MB\n" +
                "Ace_Stream_Media_3.1.2.exe1                                xrw   последнее изменение 18.03.2016 05:33:05   75MB\n");
    }

    @Test
    @Tag("Exception")
    void Exception() throws IOException {
        assertThrows(NullPointerException.class, () -> commandLine("-l -h -r testI".split(" ")));
    }

    @Test
    @Tag("Exception")
    void Exception2() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> commandLine("-l -h -o testIn".split(" ")));
    }

    @Test
    @Tag("Exception")
    void Exception3() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> commandLine("-l -h -o lala\\lala testIn".split(" ")));
    }
//    @Test
//    @Tag("---d")
//    void ___d() throws Exception {
//        assertEquals(commandLine("testIn".split(" ")), "Ace_Stream_Media_3.1.2.exe\n" +
//                "AdbeRdr930_ru_RU.exe\n" +
//                "atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip\n" +
//                "avast_free_antivirus_setup_online_comss.exe\n" +
//                "Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe\n" +
//                "DTLiteInstaller.exe\n" +
//                "Git-2.10.0-64-bit.exe\n" +
//                "K-Lite_Codec_Pack_960_Mega.exe\n" +
//                "KMPlayer 2.9.4.1434 Final.exe\n" +
//                "mseinstall.exe\n" +
//                "online-consultant.exe\n" +
//                "TeamViewerPortable\n" +
//                "teeworlds-0.5.1-win32\n" +
//                "Tor Browser\n" +
//                "тема\n");
//    }
//
//    @Test
//    @Tag("l--f")
//    void ___f() throws IOException {
//        assertEquals(test("testIn\\Ace_Stream_Media_3.1.2.exe"), "testIn\\Ace_Stream_Media_3.1.2.exe\n");
//    }
//
//    @Test
//    @Tag("lh-f")
//    void lh_f() throws IOException {
//        assertEquals(test("ls -l -h testIn\\Ace_Stream_Media_3.1.2.exe"), "testIn\\Ace_Stream_Media_3.1.2.exe  xrw   последнее изменение 18.03.2016 05:33:05   75MB\n");
//    }
//
//    @Test
//    @Tag("---d")
//    void l__f() throws IOException {
//        assertEquals(test("ls -l testIn\\Ace_Stream_Media_3.1.2.exe"), "testIn\\Ace_Stream_Media_3.1.2.exe  111 1458311585083 79522432\n");
//    }
//
//    @Test
//    @Tag("l--d")
//    void l__d() throws IOException {
//        assertEquals(test("ls -l  testIn"), "testIn\\Ace_Stream_Media_3.1.2.exe                                111 1458311585083 79522432\n" +
//                "testIn\\AdbeRdr930_ru_RU.exe                                      111 1279732176000 25879608\n" +
//                "testIn\\atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip        111 1438626227247 81475851\n" +
//                "testIn\\avast_free_antivirus_setup_online_comss.exe               111 1438876146121 5481336\n" +
//                "testIn\\Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe  111 1273572330000 65762043\n" +
//                "testIn\\DTLiteInstaller.exe                                       111 1438952882607 1709792\n" +
//                "testIn\\Git-2.10.0-64-bit.exe                                     111 1473356327247 33049944\n" +
//                "testIn\\K-Lite_Codec_Pack_960_Mega.exe                            111 1355557062417 24222983\n" +
//                "testIn\\KMPlayer 2.9.4.1434 Final.exe                             111 1338740319172 29391072\n" +
//                "testIn\\mseinstall.exe                                            111 1386123358172 13697208\n" +
//                "testIn\\online-consultant.exe                                     111 1450679908864 15745976\n" +
//                "testIn\\TeamViewerPortable                                        111 1490861124666 4096\n" +
//                "testIn\\teeworlds-0.5.1-win32                                     111 1490861124716 0\n" +
//                "testIn\\Tor Browser                                               111 1490861151914 0\n" +
//                "testIn\\тема                                                      111 1490861167273 4096\n");
//    }
//
//    @Test
//    @Tag("lh-d")
//    void lh_d() throws IOException {
//        assertEquals(test("ls -l -h testIn"), "Ace_Stream_Media_3.1.2.exe                                xrw   последнее изменение 18.03.2016 05:33:05   75MB\n" +
//                "AdbeRdr930_ru_RU.exe                                      xrw   последнее изменение 21.07.2010 09:09:36   24MB\n" +
//                "atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip        xrw   последнее изменение 03.08.2015 09:23:47   77MB\n" +
//                "avast_free_antivirus_setup_online_comss.exe               xrw   последнее изменение 06.08.2015 06:49:06   5MB\n" +
//                "Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe  xrw   последнее изменение 11.05.2010 02:05:30   62MB\n" +
//                "DTLiteInstaller.exe                                       xrw   последнее изменение 07.08.2015 04:08:02   1MB\n" +
//                "Git-2.10.0-64-bit.exe                                     xrw   последнее изменение 08.09.2016 08:38:47   31MB\n" +
//                "K-Lite_Codec_Pack_960_Mega.exe                            xrw   последнее изменение 15.12.2012 11:37:42   23MB\n" +
//                "KMPlayer 2.9.4.1434 Final.exe                             xrw   последнее изменение 03.06.2012 08:18:39   28MB\n" +
//                "mseinstall.exe                                            xrw   последнее изменение 04.12.2013 06:15:58   13MB\n" +
//                "online-consultant.exe                                     xrw   последнее изменение 21.12.2015 09:38:28   15MB\n" +
//                "TeamViewerPortable                                        xrw   последнее изменение 30.03.2017 11:05:24   (Папка) 4KB\n" +
//                "teeworlds-0.5.1-win32                                     xrw   последнее изменение 30.03.2017 11:05:24   (Папка) 0Byte\n" +
//                "Tor Browser                                               xrw   последнее изменение 30.03.2017 11:05:51   (Папка) 0Byte\n" +
//                "тема                                                      xrw   последнее изменение 30.03.2017 11:06:07   (Папка) 4KB\n");
//    }
//    @Test
//    @Tag("--rd")
//    void ___dr() throws IOException {
//        assertEquals(test("ls -r testIn"), "тема\n" +
//                "Tor Browser\n" +
//                "teeworlds-0.5.1-win32\n" +
//                "TeamViewerPortable\n" +
//                "online-consultant.exe\n" +
//                "mseinstall.exe\n" +
//                "KMPlayer 2.9.4.1434 Final.exe\n" +
//                "K-Lite_Codec_Pack_960_Mega.exe\n" +
//                "Git-2.10.0-64-bit.exe\n" +
//                "DTLiteInstaller.exe\n" +
//                "Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe\n" +
//                "avast_free_antivirus_setup_online_comss.exe\n" +
//                "atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip\n" +
//                "AdbeRdr930_ru_RU.exe\n" +
//                "Ace_Stream_Media_3.1.2.exe\n");
//    }
//
//    @Test
//    @Tag("l-rf")
//    void ___fr() throws IOException {
//        assertEquals(test("ls -r testIn\\Ace_Stream_Media_3.1.2.exe"), "testIn\\Ace_Stream_Media_3.1.2.exe\n");
//    }
//
//    @Test
//    @Tag("lhrf")
//    void lh_fr() throws IOException {
//        assertEquals(test("ls -l -h -r testIn\\Ace_Stream_Media_3.1.2.exe"), "testIn\\Ace_Stream_Media_3.1.2.exe  xrw   последнее изменение 18.03.2016 05:33:05   75MB\n");
//    }
//
//    @Test
//    @Tag("--rf")
//    void l__fr() throws IOException {
//        assertEquals(test("ls -l -r testIn\\Ace_Stream_Media_3.1.2.exe"), "testIn\\Ace_Stream_Media_3.1.2.exe  111 1458311585083 79522432\n");
//    }
//
//    @Test
//    @Tag("l-rd")
//    void l__dr() throws IOException {
//        assertEquals(test("ls -l -r testIn"), "testIn\\тема                                                      111 1490861167273 4096\n" +
//                "testIn\\Tor Browser                                               111 1490861151914 0\n" +
//                "testIn\\teeworlds-0.5.1-win32                                     111 1490861124716 0\n" +
//                "testIn\\TeamViewerPortable                                        111 1490861124666 4096\n" +
//                "testIn\\online-consultant.exe                                     111 1450679908864 15745976\n" +
//                "testIn\\mseinstall.exe                                            111 1386123358172 13697208\n" +
//                "testIn\\KMPlayer 2.9.4.1434 Final.exe                             111 1338740319172 29391072\n" +
//                "testIn\\K-Lite_Codec_Pack_960_Mega.exe                            111 1355557062417 24222983\n" +
//                "testIn\\Git-2.10.0-64-bit.exe                                     111 1473356327247 33049944\n" +
//                "testIn\\DTLiteInstaller.exe                                       111 1438952882607 1709792\n" +
//                "testIn\\Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe  111 1273572330000 65762043\n" +
//                "testIn\\avast_free_antivirus_setup_online_comss.exe               111 1438876146121 5481336\n" +
//                "testIn\\atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip        111 1438626227247 81475851\n" +
//                "testIn\\AdbeRdr930_ru_RU.exe                                      111 1279732176000 25879608\n" +
//                "testIn\\Ace_Stream_Media_3.1.2.exe                                111 1458311585083 79522432\n");
//    }
//
//    @Test
//    @Tag("lhrd")
//    void lh_dr() throws IOException {
//        assertEquals(test("ls -l -h -r testIn"), "тема                                                      xrw   последнее изменение 30.03.2017 11:06:07   (Папка) 4KB\n" +
//                "Tor Browser                                               xrw   последнее изменение 30.03.2017 11:05:51   (Папка) 0Byte\n" +
//                "teeworlds-0.5.1-win32                                     xrw   последнее изменение 30.03.2017 11:05:24   (Папка) 0Byte\n" +
//                "TeamViewerPortable                                        xrw   последнее изменение 30.03.2017 11:05:24   (Папка) 4KB\n" +
//                "online-consultant.exe                                     xrw   последнее изменение 21.12.2015 09:38:28   15MB\n" +
//                "mseinstall.exe                                            xrw   последнее изменение 04.12.2013 06:15:58   13MB\n" +
//                "KMPlayer 2.9.4.1434 Final.exe                             xrw   последнее изменение 03.06.2012 08:18:39   28MB\n" +
//                "K-Lite_Codec_Pack_960_Mega.exe                            xrw   последнее изменение 15.12.2012 11:37:42   23MB\n" +
//                "Git-2.10.0-64-bit.exe                                     xrw   последнее изменение 08.09.2016 08:38:47   31MB\n" +
//                "DTLiteInstaller.exe                                       xrw   последнее изменение 07.08.2015 04:08:02   1MB\n" +
//                "Delphi7_Lite_Full_Setup_v7.3.3.3v3(Build_2010-02-02).exe  xrw   последнее изменение 11.05.2010 02:05:30   62MB\n" +
//                "avast_free_antivirus_setup_online_comss.exe               xrw   последнее изменение 06.08.2015 06:49:06   5MB\n" +
//                "atheros_ar5xxx_ar9xxx_wireless_10_0_0_298_whql.zip        xrw   последнее изменение 03.08.2015 09:23:47   77MB\n" +
//                "AdbeRdr930_ru_RU.exe                                      xrw   последнее изменение 21.07.2010 09:09:36   24MB\n" +
//                "Ace_Stream_Media_3.1.2.exe                                xrw   последнее изменение 18.03.2016 05:33:05   75MB\n");
//    }
}