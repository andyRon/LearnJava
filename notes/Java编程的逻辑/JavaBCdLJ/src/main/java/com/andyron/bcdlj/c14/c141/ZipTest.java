package com.andyron.bcdlj.c14.c141;

import com.andyron.bcdlj.c13.InputStreamOutputStreamTest;

import java.io.*;
import java.util.zip.*;

/**
 * @author andyron
 **/
public class ZipTest {
    public static void main(String[] args) throws IOException {
//        gzip("students.txt");
//        gunzip("students.txt.gz", "students2.txt");

//        File dir = new File("./data");
//        File zipFile = new File("data.zip");
//        zip(dir, zipFile);

//        unzip(zipFile, "data2/");
    }


    public static void gzip(String fileName) throws IOException {
        InputStream in = null;
        String gzipFileName = fileName + ".gz";
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fileName));
            out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(gzipFileName)));
            InputStreamOutputStreamTest.copy(in, out);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    public static void gunzip(String gzipFileName, String unzipFileName) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new GZIPInputStream(new BufferedInputStream(new FileInputStream(gzipFileName)));
            out = new BufferedOutputStream(new FileOutputStream(unzipFileName));
            InputStreamOutputStreamTest.copy(in, out);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * rootPath表示父目录，用于计算每个文件的相对路径
     * @param inFile 输入，可以是普通文件或目录
     * @param zipFile 输出
     * @throws IOException
     */
    public static void zip(File inFile, File zipFile) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
        try {
            if (!inFile.exists()) {
                throw new FileNotFoundException(inFile.getAbsolutePath());
            }
            inFile = inFile.getCanonicalFile();
            String rootPath = inFile.getParent();
            if (!rootPath.endsWith(File.separator)) {
                rootPath += File.separator;
            }
            addFileToZipOut(inFile, out, rootPath);
        } finally {
            out.close();
        }
    }

    private static void addFileToZipOut(File file, ZipOutputStream out, String rootPath) throws IOException {
        String relativePath = file.getCanonicalPath().substring(rootPath.length());
        if (file.isFile()) {
            out.putNextEntry(new ZipEntry(relativePath));
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            try {
                InputStreamOutputStreamTest.copy(in, out);
            } finally {
                in.close();
            }
        } else {
            out.putNextEntry(new ZipEntry(relativePath + File.separator));
            for (File f : file.listFiles()) {
                addFileToZipOut(f, out, rootPath);
            }
        }
    }

    public static void unzip(File zipFile, String destDir) throws IOException {
        ZipInputStream zi = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
        if (!destDir.endsWith(File.separator)) {
            destDir += File.separator;
        }
        try {
            ZipEntry entry = zi.getNextEntry();
            while (entry != null) {
                extractZipEntry(entry, zi, destDir);
                entry = zi.getNextEntry();
            }
        } finally {
            zi.close();
        }
    }

    /**
     * 处理每个压缩条目
     * @param entry
     * @param zi
     * @param destDir
     * @throws IOException
     */
    private static void extractZipEntry(ZipEntry entry, ZipInputStream zi, String destDir) throws IOException {
        if (!entry.isDirectory()) {
            File parent = new File(destDir + entry.getName()).getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            OutputStream entryOut = new BufferedOutputStream(new FileOutputStream(destDir + entry.getName()));
            try {
                InputStreamOutputStreamTest.copy(zi, entryOut);
            } finally {
                entryOut.close();
            }
        } else {
            new File(destDir + entry.getName()).mkdirs();
        }
    }
}
