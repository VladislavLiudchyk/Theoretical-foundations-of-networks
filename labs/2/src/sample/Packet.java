package sample;

import com.google.common.primitives.Bytes;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Packet {

    private static String flag = "10000001";
    private static String fcs = "10101010";
    private String srcAddress;
    private String destAddress;
    private String data;
    private List<String> packets;
    private byte[] bytesToSend;
    private List<byte[]> bytePackets;

    public Packet() {
        packets = new ArrayList<>();
        bytePackets = new ArrayList<>();
        srcAddress = "";
        destAddress = "";
    }
    public String decode(byte[] value) {
        String reverse = "";
        String zeroByte = "00000000";
        String buf = "";
        String gg = "";
        for(int a = 0; a < value.length; a++) {
            buf = Integer.toUnsignedString(Byte.toUnsignedInt(value[a]), 2);
            buf = zeroByte.substring(0, 8 - buf.length()) + buf;
            reverse = reverse + buf;
        }
        System.out.println("Check reverse length = " + reverse.length() + "and ostatok ot del na sebya = " + (reverse.length() % 11));
        if(!((reverse.length() % 11) == 0))
            reverse = reverse.replaceAll("100000001", "10000001");
        reverse = reverse.substring(24, reverse.length() - 8);
        reverse = reverse.substring(0, reverse.length() - (reverse.length() % 8));

        for(int a = 0; (reverse.length() - 8*(a+1)) != 0 && reverse.length() > 8; a ++) {
            buf = reverse.substring(reverse.length() - 8*(a+1), reverse.length() - 8*a);
            if (buf.equals("00000000"))
                reverse = reverse.replaceAll("00000000", "");
        }

        System.out.println("Reverse string: " + reverse);
        System.out.println("Reverse length: " + reverse.length());
        System.out.println("Ostatok: " + (reverse.length()%8));

        String ch = "";
        List<Byte> byteList1 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        for(String str : reverse.split("(?<=\\G.{8})")) {
            list1.add(Integer.parseInt(str, 2));
        }
        for(int a = 0; a < list1.size(); a++)
            byteList1.add(list1.get(a).byteValue());
        try {
            String ggg = new String(Bytes.toArray(byteList1), "Windows-1251");
            System.out.println("Input string: " + ggg);
            gg = ggg;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return gg;
    }

    public List<byte[]> encode(String value) {
        String temp = "";
        data = fromStringToByte(value);
        packets.clear();
        for(int i = 0; i < data.length(); i = i+56) {
            if(i+56 >= data.length()) {
                temp = data.substring(i, data.length());
                while(temp.length() < 56 && !data.contains("10000001"))
                    temp = temp + "0";
            }
            else
                temp = data.substring(i,i+56);
            if(temp.contains(flag)) {
                temp = temp.replaceAll("10000001", "100000001");
                double k = temp.length()/64;
                System.out.println("Double k = " + k);
                int kof = (int) k;
                System.out.println("Integer k = " + kof);
                System.out.println("String " + temp + " Length = " + temp.length());
                System.out.println("Num of bits = " + ((kof+1)*64 - temp.length()));
                for(; temp.length() < ((kof+1)*64);)
                    temp = temp + "0";
                System.out.println(temp);
            }
            System.out.println(flag + " " + srcAddress + " " + destAddress + " " + temp + " " + fcs);
            packets.add(flag + srcAddress + destAddress + temp + fcs);
        }

        List<Byte> byteList = new ArrayList<>();
        bytePackets.clear();
        for(int i = 0; i < packets.size(); i++) {
            if(packets.get(i).isEmpty())
                break;
            byteList.clear();
            System.out.println("Packet №" + (i+1) + " " + packets.get(i));
            List<Integer> list = new ArrayList<>();
            for(String str : packets.get(i).split("(?<=\\G.{8})")) {
                list.add(Integer.parseInt(str, 2));
            }
            for(int a = 0; a < list.size(); a++)
                byteList.add(list.get(a).byteValue());
            System.out.println(list);
            System.out.println("Unsigned int from byte -32: " + Byte.toUnsignedInt(byteList.get(4)));
            System.out.println(byteList);
            bytesToSend = Bytes.toArray(byteList);
            bytePackets.add(bytesToSend);
            for(int a = 0; a < bytePackets.size(); a++)
                for(int b = 0; b < bytePackets.get(a).length; b++) {
                    System.out.print((bytePackets.get(a))[b] + "   ");
                    System.out.println("\n");
                }

        }
        return bytePackets;

    }

    public void decodeAll(List<byte[]> value) {
        data = "";
        for(int i =0; i < value.size(); i++)
            data += decode(value.get(i));
        System.out.println("Decoded string\n" + data);
    }

    public void setErrorFCS() {
        fcs = "10111110";
    }

    public List<byte[]> getBytePackets() {
        return bytePackets;
    }

    public void setScrAddress(Integer srcAddress) {
        try {
            this.srcAddress = fromIntToByte(srcAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDestAddress(Integer destAddress) {
        try {
            this.destAddress = fromIntToByte(destAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPacketData(String data) {
        this.data = data;
    }

    public String getPacketData() {
        return data;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public String getDestAddress() {
        return  destAddress;
    }

    public List<String> getPackets() {
        return packets;
    }

    public String getData() {
        return data;
    }

    public static String fromIntToByte(int value) {
       byte b = (byte) value;
       String emptyByte = "00000000";
       int i2 = b & 0xFF;
       String flg = Integer.toUnsignedString(value, 2);
       flg = emptyByte.substring(0, 8 - flg.length()) + flg;
       System.out.println("From int to byte " + flg);
       fromIntToHexString(value);
       return flg;
    }

    public static String fromStringToByte(String value) {
        String temp = "";
        try {
            System.out.println("Parse string ");
            for(int i = 0; i<value.getBytes("Windows-1251").length; i++) {
                temp = temp + fromIntToByte(Byte.toUnsignedInt(value.getBytes("Windows-1251")[i]));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return temp;
    }


    public static String fromIntToHexString(int value) {
        String emptyHex;
        if(value < 16) {
            emptyHex = "0x0";
            emptyHex = emptyHex + Integer.toHexString(value);

        }
        else {
            emptyHex = "0x";
            emptyHex = emptyHex + Integer.toHexString(value);
        }
        System.out.println("\nFrom int to hex " + emptyHex);
        return emptyHex;
    }

    public String HexStringPacket(byte[] pack) {
            return String.format("%x", new BigInteger(1, pack));
    }

    public void setCorrectFCS() {
        fcs = "10101010";
    }

    public String getFlag() {
        return flag;
    }

}
