package pl.przydan.HelloServerApp;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;


public class HelloApp {
    public static void main(String[] args) throws Exception {
        helloServerStart();
    }

    private static void helloServerStart() throws Exception {
        WebAppContext webapp = WebAppInit();
        serverInit(webapp);
    }

    private static void serverInit(WebAppContext webapp) throws Exception {
        var server = new Server(8080);
        server.setHandler(webapp);
        server.start();
        server.join();
    }

    private static WebAppContext WebAppInit() {
        var webapp = new WebAppContext();
        webapp.setResourceBase("src/main/webapp");
        webapp.setContextPath("/");
        webapp.setConfigurations(new Configuration[]
                {
                        new AnnotationConfiguration(),
                        new WebInfConfiguration(),
                        new WebXmlConfiguration(),
                        new MetaInfConfiguration(),
                        new FragmentConfiguration(),
                        new EnvConfiguration(),
                        new PlusConfiguration(),
                        new JettyWebXmlConfiguration()
                });
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
        return webapp;
    }
}

