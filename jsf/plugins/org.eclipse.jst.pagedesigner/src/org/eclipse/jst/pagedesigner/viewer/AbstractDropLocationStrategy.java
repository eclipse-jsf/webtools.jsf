package org.eclipse.jst.pagedesigner.viewer;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;

/**
 * Abstract parent of all concrete client implmentation of IDropLocationStrategy's
 * Clients should sub-class instead of implementing {@link IDropLocationStrategy} directly
 * to avoid future API breakage
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDropLocationStrategy extends GraphicalEditPolicy implements IDropLocationStrategy 
{
    public AbstractDropLocationStrategy(EditPart host)
    {
        setHost(host);
    }
    
    public abstract DesignPosition calculateDesignPosition(EditPart host, Point p,
            IPositionMediator validator);

    public abstract List showTargetFeedback(EditPart host, DesignPosition position, ChangeBoundsRequest request);
}
