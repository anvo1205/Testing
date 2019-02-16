package Utility;

import java.io.IOException;
import java.util.List;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;

public class PdfUtils {
	
	public static PDFUtil pdfUtil = new PDFUtil(); 
	
	/* Method name	: comparePdfContent
	 * Description	: This method will get total pages of a pdf file
	 * Accept		: String (filePath)
	 * Return		: int
	 */	
	public static int getPageCount(String filePath) throws IOException
	{
		return pdfUtil.getPageCount(filePath);
	}
	
	/* Method name	: getPdfText
	 * Description	: This method will get all texts of a pdf file
	 * Accept		: String (filePath)
	 * Return		: String
	 */	
	public static String getPdfText(String filePath) throws IOException
	{
		return pdfUtil.getText(filePath);
	}
	
	/* Method name	: getPdfText
	 * Description	: This method will get all texts of a pdf file in a range pages
	 * Accept		: String (filePath), int (1st page & last page)
	 * Return		: String
	 */	
	public static String getPdfText(String filePath, int fromPage, int toPage) throws IOException
	{
		return pdfUtil.getText(filePath, fromPage, toPage);
	}
	
	/* Method name	: extractImages
	 * Description	: This method will get all images of a pdf file
	 * Accept		: String (filePath and destinationPath), int (1st page & last page)
	 * Return		: List<String>
	 */	
	public static List<String> extractImages(String filePath, String destinationPath) throws IOException
	{
		 pdfUtil.setImageDestinationPath(destinationPath);
		return pdfUtil.extractImages(filePath);
	}
	
	/* Method name	: extractImages
	 * Description	: This method will get all images of a pdf file in a range pages
	 * Accept		: String (filePath and destinationPath), int (1st page & last page)
	 * Return		: List<String>
	 */	
	public static List<String> extractImages(String filePath, String destinationPath, int fromPage, int toPage) throws IOException
	{
		 pdfUtil.setImageDestinationPath(destinationPath);
		return pdfUtil.extractImages(filePath, 2, 2);
	}
	
	/* Method name	: savePDFAsImage
	 * Description	: This method will save pdf as image
	 * Accept		: String (filePath and destinationPath)
	 * Return		: List<String>
	 */	
	public static List<String> savePDFAsImage(String filePath, String destinationPath) throws IOException
	{
		pdfUtil.setImageDestinationPath(destinationPath);
		return pdfUtil.savePdfAsImage(filePath);
	}
	
	/* Method name	: comparePdfContent
	 * Description	: This method will compare content of 2 pdf files
	 * Accept		: String (filePath)
	 * Return		: boolean
	 */	
	public static boolean comparePdfContent(String expectedFilePath, String actualFilePath, boolean visualMode) throws IOException
	{
		if (visualMode)
		{
			pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);//default mode is text mode
		}
		pdfUtil.highlightPdfDifference(true);
		pdfUtil.setImageDestinationPath(Constants.Download_Destination);
		return pdfUtil.compare(expectedFilePath, actualFilePath);
	}
	
	/* Method name	: compareSpecificPage
	 * Description	: This method will compare content a specific page of source and destination files
	 * Accept		: String (filePath), boolean (visual or text mode), int (page number)
	 * Return		: boolean
	 */	
	public static boolean compareSpecificPage(String expectedFilePath, String actualFilePath
											, boolean visualMode, int srcPageNum, int desPageNum) throws IOException
	{
		if (visualMode)
		{
			pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);//default mode is text mode
		}
		pdfUtil.highlightPdfDifference(true);
		pdfUtil.setImageDestinationPath(Constants.Download_Destination);
		return pdfUtil.compare(expectedFilePath, actualFilePath, srcPageNum, desPageNum);
	}
	
	/* Method name	: comparePdfContent
	 * Description	: This method will compare content of 2 pdf files
	 * Accept		: String (filePath)
	 * Return		: boolean
	 */	
	public static boolean compareAfterRemoveText(String expectedFilePath, String actualFilePath, boolean visualMode, List<String> removedTexts) throws IOException
	{
		for (int i = 0; i < removedTexts.size(); i++)
		{
			pdfUtil.excludeText(removedTexts.get(i));
		}
		if (visualMode)
		{
			pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);//default mode is text mode
		}
		pdfUtil.highlightPdfDifference(true);
		pdfUtil.setImageDestinationPath("C:/John/Venngage/Download");
		return pdfUtil.compare(expectedFilePath, actualFilePath);
	}
}
