package com.linkon.common.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.tools.view.ViewToolContext;

import com.linkon.common.util.ConfUtil;

public class AbsoluteUrlDirective extends AbstractDirective{

	private static String localUrl = ConfUtil.getConf("LOCAL_URL", "");
	
    static {
        if(localUrl == "") {
            throw new ResourceNotFoundException("请配置属性：『LOCAL_URL』!");
        } else {
            if(localUrl.endsWith("/")) {
                localUrl = localUrl.substring(0, localUrl.length() - 1);
            }

        }
    }
    
	@Override
	protected boolean doRender(InternalContextAdapter internalContext, ViewToolContext context, Writer writer, Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		SimpleNode sn = (SimpleNode) node.jjtGetChild(0);
		String url = (String) sn.value(internalContext);
		if (localUrl == "") {
			throw new ResourceNotFoundException("no LOCAL_URL found, please set it in the config.properties!");
		} else {
			if (url.startsWith("/")) {
				url = localUrl + url;
			} else {
				url = localUrl + "/" + url;
			}

			writer.write(url);
			return true;
		}
	}

	@Override
	public String getName() {
		return "url";
	}

	@Override
	public int getType() {
		return 2;
	}

}
