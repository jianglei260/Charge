package com.wuxian.charge.utils;

import java.math.BigInteger;

/**
 * Created by jianglei on 2017/3/2.
 */

public class ByteUtil {
    public static byte[] HexString2Bytes(String src) {
        byte[] ret = new byte[src.length() / 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < tmp.length / 2; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    /**
     * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
     *
     * @param src0
     *            byte
     * @param src1
     *            byte
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
                .byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
                .byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    public static String shi2HexStr(int progress) {
        BigInteger target = new BigInteger(String.valueOf(progress));
        if (progress < 10) {
            return "0" + target.toString(16);
        } else if (progress >= 10 && progress < 16) {
            return "0" + target.toString(16);
        }
        return target.toString(16);
    }
}
