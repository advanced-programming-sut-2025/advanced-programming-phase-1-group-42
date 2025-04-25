package org.example.models.goods.tools;

import org.example.models.goods.Good;

public abstract class Tool extends Good {
    private ToolType toolType;

    public ToolType getToolType() {
        return toolType;
    }


}
