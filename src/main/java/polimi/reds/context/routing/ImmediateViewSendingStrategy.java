/***
 * * REDS - REconfigurable Dispatching System
 * * Copyright (C) 2003 Politecnico di Milano
 * * <mailto: cugola@elet.polimi.it> <mailto: picco@elet.polimi.it>
 * *
 * * This library is free software; you can redistribute it and/or modify it
 * * under the terms of the GNU Lesser General Public License as published by
 * * the Free Software Foundation; either version 2.1 of the License, or (at
 * * your option) any later version.
 * *
 * * This library is distributed in the hope that it will be useful, but
 * * WITHOUT ANY WARRANTY; without even the implied warranty of
 * * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * * General Public License for more details.
 * *
 * * You should have received a copy of the GNU Lesser General Public License
 * * along with this library; if not, write to the Free Software Foundation,
 * * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 ***/

package polimi.reds.context.routing;

import polimi.reds.NodeDescriptor;

/**
 * This strategy calculates, simplifies and sends a view as soon as the method
 * is called, without delay or other optimization.
 * 
 */
public class ImmediateViewSendingStrategy extends ViewSendingStrategy {

	/*
	 * 
	 */
	public ImmediateViewSendingStrategy(CARouter caRouter) {
		super(caRouter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * polimi.reds.context.routing.ViewSenderStrategy#sendViewDueTo(polimi.reds
	 * .NodeDescriptor)
	 */
	public void sendViewDueTo(NodeDescriptor node) {

		for (NodeDescriptor destinationNode : caRouter.getContextTable().getNodes()) {
			if (!destinationNode.equals(node) && destinationNode.isBroker()) {

				super.createViewAndPutInTable(destinationNode);

				super.simplifyViewAndPutInTable(destinationNode);

				super.sendViewAndPutInTable(destinationNode);
			}
		}

	}

}
