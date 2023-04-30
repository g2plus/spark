package top.arhi.util;

import com.spire.pdf.PdfDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ConvertPdf2Img {

	/**
	 * 
	 * @param pdfFile
	 *            PDF文件
	 * @param imgPath
	 *            图片存储路径
	 * @param imgName
	 *            图片存储文件名
	 * @param imgType
	 *            图片存储类型
	 * @param dpi
	 *            转换精度，建议100左右
	 * @return 是否成功
	 * @throws IOException
	 */
	public static boolean PDFToImg(File pdfFile, String imgPath, String imgName, String imgType, int dpi)
			throws IOException {

		String color = "rgb";
		int startPage = 1;

		//获取pdf的页面数
		PdfDocument pdf = new PdfDocument();
		pdf.loadFromFile(pdfFile.getAbsolutePath());

		int endPage = pdf.getPages().getCount();
		float cropBoxLowerLeftX = 0.0F;
		float cropBoxLowerLeftY = 0.0F;
		float cropBoxUpperRightX = 0.0F;
		float cropBoxUpperRightY = 0.0F;
		PDDocument pdDocument = null;

		try {
			pdDocument = PDDocument.load(pdfFile);
			ImageType imageType = null;
			if ("bilevel".equalsIgnoreCase(color)) {
				imageType = ImageType.BINARY;
			} else if ("gray".equalsIgnoreCase(color)) {
				imageType = ImageType.GRAY;
			} else if ("rgb".equalsIgnoreCase(color)) {
				imageType = ImageType.RGB;
			} else if ("rgba".equalsIgnoreCase(color)) {
				imageType = ImageType.ARGB;
			}

			if (imageType == null) {
				return false;
			}

			if (cropBoxLowerLeftX != 0.0F || cropBoxLowerLeftY != 0.0F || cropBoxUpperRightX != 0.0F
					|| cropBoxUpperRightY != 0.0F) {
				changeCropBox(pdDocument, cropBoxLowerLeftX, cropBoxLowerLeftY, cropBoxUpperRightX, cropBoxUpperRightY);
			}

			boolean success = true;
			endPage = Math.min(endPage, pdDocument.getNumberOfPages());
			PDFRenderer renderer = new PDFRenderer(pdDocument);


			for (int endTime = startPage - 1; endTime < endPage; ++endTime) {
				BufferedImage image = renderer.renderImageWithDPI(endTime, (float) dpi, imageType);
				String format = "%0"+Integer.valueOf(endPage).toString().length()+"d";
				String number=String.format(format,endTime+1);
				String duration = imgPath + imgName +number+"." + imgType;
				System.out.println("PDF图片生成路径" + duration);
				success &= ImageIOUtil.writeImage(image, duration, dpi);
			}

			if (!success) {
				System.out.println("Error: no writer found for image format \'" + imgType + "\'");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pdDocument != null) {
				pdDocument.close();
			}
		}
		return true;
	}

	private static void changeCropBox(PDDocument document, float a, float b, float c, float d) {
		Iterator<PDPage> point = document.getPages().iterator();
		while (point.hasNext()) {
			PDPage page = point.next();
			PDRectangle rectangle = new PDRectangle();
			rectangle.setLowerLeftX(a);
			rectangle.setLowerLeftY(b);
			rectangle.setUpperRightX(c);
			rectangle.setUpperRightY(d);
			page.setCropBox(rectangle);
		}
	}
}
