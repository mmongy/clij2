package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.interfaces.ClearCLImageInterface;
import net.haesleinhuepf.clij.macro.CLIJMacroPlugin;
import net.haesleinhuepf.clij.macro.CLIJOpenCLProcessor;
import net.haesleinhuepf.clij.macro.documentation.OffersDocumentation;
import net.haesleinhuepf.clij2.AbstractCLIJ2Plugin;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clij2.utilities.HasClassifiedInputOutput;
import net.haesleinhuepf.clij2.utilities.IsCategorized;
import org.scijava.plugin.Plugin;

import java.util.HashMap;

/**
 * 	Author: @haesleinhuepf
 * 	        August 2019
 */

@Plugin(type = CLIJMacroPlugin.class, name = "CLIJ2_greater")
public class Greater extends AbstractCLIJ2Plugin implements CLIJMacroPlugin, CLIJOpenCLProcessor, OffersDocumentation, IsCategorized, HasClassifiedInputOutput {
    @Override
    public String getInputType() {
        return "Image";
    }

    @Override
    public String getOutputType() {
        return "Image";
    }

    @Override
    public String getCategories() {
        return "Math";
    }

    @Override
    public boolean executeCL() {
        boolean result = getCLIJ2().greater((ClearCLBuffer)( args[0]), (ClearCLBuffer)(args[1]), (ClearCLBuffer)(args[2]));
        return result;
    }

    public static boolean greater(CLIJ2 clij2, ClearCLImageInterface src1, ClearCLImageInterface src2, ClearCLImageInterface dst) {

        HashMap<String, Object> parameters = new HashMap<>();
        
        parameters.clear();
        parameters.put("src1", src1);
        parameters.put("src2", src2);
        parameters.put("dst", dst);

        clij2.execute(Greater.class, "greater_" + src1.getDimension() + "d_x.cl", "greater_" + src1.getDimension() + "d", src1.getDimensions(), src1.getDimensions(), parameters);
        return true;
    }
        
    
    
    @Override
    public String getParameterHelpText() {
        return "Image source1, Image source2, ByRef Image destination";
    }

    @Override
    public String getDescription() {
        return "Determines if two images A and B greater pixel wise.\n\nf(a, b) = 1 if a > b; 0 otherwise. ";
    }
    
    @Override
    public String getAvailableForDimensions() {
        return "2D, 3D";
    }
}
