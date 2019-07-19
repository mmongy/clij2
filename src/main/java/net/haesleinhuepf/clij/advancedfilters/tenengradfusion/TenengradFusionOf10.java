package net.haesleinhuepf.clij.advancedfilters.tenengradfusion;

import net.haesleinhuepf.clij.macro.CLIJMacroPlugin;
import org.scijava.plugin.Plugin;

@Plugin(type = CLIJMacroPlugin.class, name = "CLIJ_tenengradFusionOf10")
public class TenengradFusionOf10 extends AbstractTenengradFusion {

    @Override
    public String getParameterHelpText() {
        return getParameterHelpText(10);
    }


}
