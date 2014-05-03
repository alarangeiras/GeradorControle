package br.com.allanlarangeiras.geradorcontrole.util;


public class Compact
{


  private static int strlen(char[] bytes)
  {
    return bytes.length;
  }

  public static void CompBytes(char[] bytes, char[] buf)
  {
    char b1 = (char)(bytes[0] - '0');
    char b3;
    char b2 = b3 = 10;

    if (strlen(bytes) > 1) {
      b2 = (char)(bytes[1] - '0');
    }
    if (strlen(bytes) > 2) {
      b3 = (char)(bytes[2] - '0');
    }
    char b4 = '@';
    char d2 = b2;

    b2 = (char)(b2 & 0xC);
    b2 = (char)(b2 << '\002');

    d2 = (char)(d2 & 0x3);
    d2 = (char)(d2 << '\004');

    buf[0] = ((char)((b1 | b2 | b4) + '\001'));
    buf[1] = ((char)((b3 | d2 | b4) + '\001'));
  }

  public static void DecompBytes(char[] bytes, char[] buf)
  {
    char b1 = (char)(bytes[0] - '\001');
    char b2 = (char)(bytes[1] - '\001');

    char d1 = b1;
    char d2 = b2;

    b1 = (char)(b1 & 0xF);
    b2 = (char)(b2 & 0xF);

    d1 = (char)(d1 & 0x30);
    d2 = (char)(d2 & 0x30);

    d1 = (char)(d1 >> '\002');
    d2 = (char)(d2 >> '\004');

    buf[0] = ((char)(b1 + '0'));
    buf[1] = ((char)((d1 | d2) + '0'));
    buf[2] = ((char)(b2 + '0'));
    if (buf[1] - '0' == 10) buf[1] = '\000';
    if (buf[2] - '0' == 10) buf[2] = '\000';
  }

  public static char[] Compacta(char[] Texto, char[] Buffer, int TamBuf)
  {
    int TamTexto = strlen(Texto);
    if (TamTexto > (TamBuf - 1) * 3 / 2)
      return new char[0];
    int j;
    int i = j = 0;

    while ((i < TamTexto) && (j + 2 < TamBuf))
    {
      char[] bf = charPointer(Buffer, j);
      CompBytes(charPointer(Texto, i), bf);
      charPointer2(bf, Buffer, j);
      i += 3;
      j += 2;
    }

    Buffer[j] = '\000';
    Buffer = newBufferFrom(Buffer, j - 2);
    return Buffer;
  }

  public static char[] charPointer(char[] buf, int pointer) {
    char[] tmp = new char[buf.length - pointer];
    System.arraycopy(buf, pointer, tmp, 0, buf.length - pointer);
    return tmp;
  }
  public static void charPointer2(char[] orig, char[] dest, int pointer) {
    System.arraycopy(orig, 0, dest, pointer, orig.length);
  }

  public static char[] Descompacta(char[] Texto, char[] Buffer, int TamBuf)
  {
    if (Texto.length % 2 != 0) {
      return new char[0];
    }
    if (strlen(Texto) > (TamBuf - 1) / 3 * 2) {
      return new char[0];
    }
    int ptr = 0;
    while (ptr < Texto.length)
    {
      if ((Texto[ptr] <= '@') || (Texto[ptr] > ' '))
        return new char[0];
      ptr++;
    }

    int i = 0;
    ptr = 0;

    while ((ptr < Texto.length) && (i < TamBuf))
    {
      char[] bf = charPointer(Buffer, i);
      DecompBytes(charPointer(Texto, ptr), bf);
      charPointer2(bf, Buffer, i);
      ptr += 2;
      i += 3;
    }

    if (i >= TamBuf) {
      i -= 3;
    }
    Buffer[i] = '\000';
    Buffer = newBufferFrom(Buffer, i - 2);
    return Buffer;
  }

  public static char[] newBufferFrom(char[] buffer, int i) {
    char[] tmp = new char[i];
    System.arraycopy(buffer, 0, tmp, 0, i);
    return tmp;
  }
}