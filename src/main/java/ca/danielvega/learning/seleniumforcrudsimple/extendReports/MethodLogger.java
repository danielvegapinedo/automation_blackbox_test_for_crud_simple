package ca.danielvega.learning.seleniumforcrudsimple.extendReports;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class MethodLogger {

    @Around("execution(* *(..)) && @annotation(Step)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        System.out.println(MethodSignature.class.cast(point.getSignature()).getMethod().getName());
        System.out.println(point.getArgs());
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);

        return result;
    }

    /**
     * Metodo encargado de agregar las transacciones al log. con la notación de
     * @After denotamos que esta sera la operacion a ejecutarse despues de la
     * operacion denominada como JoinPoint, asi mismo puedes usar el @Before
     * (antes), o el @Around (antes y despues) Dentro de cada una de estas
     * debemos identificar la clase que indica la notacion, para que nos defina
     * cual es el comportamiento a ejecutar o la accion a realizar
*
     */
    @After("@annotation(com.clubprogramador.aspectj.util.LogAnotacion)")
    public void addLog(JoinPoint joinPoint) {
        System.out.println("Aspect (After) =" + joinPoint.toShortString());
        try {
            Object objeto = joinPoint.getArgs()[0];
            if (!(objeto instanceof Log)) {
                Transaccion transaccion = this.getAnnotationType(joinPoint);
                Log log = null;
                if (transaccion.equals(Transaccion.CREAR)) {
                    log = new LogCrear();
                } else if (transaccion.equals(Transaccion.MODIFICAR)) {
                    log = new LogModificar();
                } else if (transaccion.equals(Transaccion.ELIMINAR)) {
                    log = new LogEliminar();
                }

                if (log != null) {
                    log.setDescripcion(objeto.toString());
                    GeneralDAO dao = new GeneralDAOImp();
                    dao.crear(log);
                }
            }
        } catch (SecurityException e) {
            System.err.println("addLog =" + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("addLog =" + e.getMessage());
        }
    }

    /**
     * Metodo que obtiene el tipo de anotaci?n del aspecto
     *
     * @throws SecurityException
     * @throws NoSuchMethodException
*
     */
    private Transaccion getAnnotationType(JoinPoint joinPoint) throws SecurityException,
            NoSuchMethodException {
        Method metodo = this.getCallMethod(joinPoint);
        LogAnotacion anotacion = metodo.getAnnotation(LogAnotacion.class);
        return anotacion.transaccion();
    }

    /**
     * Metodo que obtiene el metodo interceptado
     *
     * @param joinPoint = Punto de enlace del aspecto
     * @return el metodo interceptado
*
     */
    private Method getCallMethod(JoinPoint joinPoint) {
        MethodSignature sig = (MethodSignature) joinPoint.getSignature();
        return sig.getMethod();
    }

}
