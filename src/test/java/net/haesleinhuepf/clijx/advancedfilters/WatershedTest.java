package net.haesleinhuepf.clijx.advancedfilters;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.WaitForUserDialog;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clijx.CLIJx;
import org.junit.Test;

import static org.junit.Assert.*;

public class WatershedTest {
    @Test
    public void testBlobs() {
        new ImageJ();
        ImagePlus imp = IJ.openImage("src/test/resources/blobs.tif");
        IJ.run(imp,"32-bit", "");
        imp.show();

        CLIJx clijx = CLIJx.getInstance();

        ClearCLBuffer input = clijx.push(imp);
        ClearCLBuffer thresholded = clijx.create(input);
        ClearCLBuffer watershed = clijx.create(input);

        clijx.threshold(input, thresholded, 127);

        Watershed.watershed(clijx, thresholded, watershed);

        watershed.close();
        thresholded.close();
        input.close();

    }
}