package uz.course.filter;


import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        filterName = "CompressResponseFilter",
        urlPatterns = {"/*"}, asyncSupported = true
)
public class CompressResponseFilter implements Filter {

    private HtmlCompressor compressor;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().matches(CharactersEncodingFilter.exclude) ||
                request.getRequestURI().startsWith("/upload")) {
            chain.doFilter(req, resp);
            return;
        }

        CharResponseWrapper responseWrapper = new CharResponseWrapper((HttpServletResponse) resp);
        chain.doFilter(req, responseWrapper);
        String servletResponse = responseWrapper.toString();
        if (!StringUtils.isEmpty(servletResponse)) {
            try {
                resp.getWriter().write(compressor.compress(servletResponse));
            } catch (Exception e) {
                System.out.println("Error :" + request.getRequestURI());
                System.out.println(servletResponse);
                e.printStackTrace();
                throw e;
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        compressor = new HtmlCompressor();
        compressor.setCompressCss(true);
        compressor.setCompressJavaScript(true);

        compressor.setYuiCssLineBreak(80);
        compressor.setYuiJsLineBreak(-1);
        compressor.setYuiJsNoMunge(true);
        compressor.setYuiJsPreserveAllSemiColons(true);

        compressor.setRemoveComments(true);
        compressor.setRemoveMultiSpaces(true);
        compressor.setRemoveIntertagSpaces(true);
        compressor.setRemoveIntertagSpaces(true);
        compressor.setRemoveMultiSpaces(true);
        compressor.setRemoveComments(true);
        compressor.setRemoveQuotes(true);

//        compressor.setJavaScriptCompressor(new ClosureJavaScriptCompressor(CompilationLevel.SIMPLE_OPTIMIZATIONS));
//        compressor.setCssCompressor(new YuiCssCompressor());
    }

    @Override
    public void destroy() {
    }

}