package net.crystalapps.mint.viewbinder.library.core;


import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import net.crystalapps.mint.viewbinder.library.utils.ObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Syed Owais Ali on 5/17/2018.
 */

@SuppressWarnings({"ConstantConditions","WeakerAccess","unused"})
public class ViewBinder {

    private static ClassFilter classFilter = () -> Object.class;

    public static void setClassFilter(@NonNull ClassFilter classFilter) {
        ViewBinder.classFilter = ObjectUtil.requireNonNull(classFilter);
    }

    public static void bind(@NonNull Activity activity) {
        bind(activity, classFilter);
    }

    public static void bind(@NonNull Dialog dialog) {
        bind(dialog, classFilter);
    }

    public static void bind(@NonNull Object obj, @NonNull View view) {
        bind(obj, view, classFilter);
    }

    public static void bind(@NonNull Activity activity, @NonNull ClassFilter classFilter) {
        ObjectUtil.requireNonNull(activity);
        ObjectUtil.requireNonNull(classFilter);
        bindView(activity, activity, classFilter);
    }

    public static void bind(@NonNull Dialog dialog, @NonNull ClassFilter classFilter) {
        ObjectUtil.requireNonNull(dialog);
        ObjectUtil.requireNonNull(classFilter);
        bindView(dialog, dialog, classFilter);
    }

    public static void bind(@NonNull Object obj, @NonNull View view, @NonNull ClassFilter classFilter) {
        ObjectUtil.requireNonNull(obj);
        ObjectUtil.requireNonNull(view);
        ObjectUtil.requireNonNull(classFilter);
        bindView(obj, view, classFilter);
    }

    private static void bindView(@NonNull Object obj, @NonNull Object holder, @NonNull ClassFilter classFilter) {

        // bind views
        Set<Field> fields = getAllFieldsOfClass(obj, classFilter);
        for (Field field : fields) {
            try {

                // bind single view
                if (field.isAnnotationPresent(BindView.class)) {
                    field.setAccessible(true);
                    if (View.class.isAssignableFrom(field.getType())) {
                        int id = field.getAnnotation(BindView.class).value();
                        field.set(obj, findView(holder, id));
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // bind click(s)
        Set<Method> methods = getAllMethodsOfClass(obj, classFilter);
        for (Method method : methods) {
            if (method.isAnnotationPresent(OnClick.class)) {
                method.setAccessible(true);
                int ids[] = method.getAnnotation(OnClick.class).value();
                for (int id : ids) {
                    View view = findView(holder, id);
                    if (view != null) {
                        view.setOnClickListener(v -> {
                            try {
                                if (method.getParameterTypes().length > 0) {
                                    method.invoke(obj, method.getParameterTypes()[0].cast(view));
                                }
                                else {
                                    method.invoke(obj);
                                }
                            } catch (IllegalAccessException | InvocationTargetException ex) {
                                ex.printStackTrace();
                            }
                        });
                    }
                }
            }
        }
    }

    @Nullable
    private static <T extends View> T findView(@NonNull Object holder, int id) {
        T view = null;
        if (holder instanceof Activity) {
           view = ((Activity) holder).findViewById(id);
        }
        else if (holder instanceof Dialog) {
            view = ((Dialog) holder).findViewById(id);
        }
        else if (holder instanceof View) {
            view = ((View) holder).findViewById(id);
        }

        return view;
    }

    private static Set<Field> getAllFieldsOfClass(@NonNull Object obj, @NonNull ClassFilter classFilter) {
        Set<Field> fieldSet = new HashSet<>();
        Class<?> view = obj.getClass();
        while ( (classFilter.getActivityClass().isAssignableFrom(view)          && !view.equals(classFilter.getActivityClass()))        ||
                (classFilter.getDialogClass().isAssignableFrom(view)            && !view.equals(classFilter.getDialogClass()))          ||
                (classFilter.getSupportFragmentClass().isAssignableFrom(view)   && !view.equals(classFilter.getSupportFragmentClass())) ||
                (classFilter.getFragmentClass().isAssignableFrom(view)          && !view.equals(classFilter.getFragmentClass()))        ||
                (classFilter.getViewHolderClass().isAssignableFrom(view)        && !view.equals(classFilter.getViewHolderClass()))      ||
                (classFilter.getViewClass().isAssignableFrom(view)              && !view.equals(classFilter.getViewClass()))
                ) {
            fieldSet.addAll(Arrays.asList(view.getDeclaredFields()));
            view = view.getSuperclass();
        }
        return fieldSet;
    }

    private static Set<Method> getAllMethodsOfClass(@NonNull Object obj, @NonNull ClassFilter classFilter) {
        Set<Method> methodSet = new HashSet<>();
        Class<?> view = obj.getClass();
        while ( (classFilter.getActivityClass().isAssignableFrom(view)          && !view.equals(classFilter.getActivityClass()))        ||
                (classFilter.getDialogClass().isAssignableFrom(view)            && !view.equals(classFilter.getDialogClass()))          ||
                (classFilter.getSupportFragmentClass().isAssignableFrom(view)   && !view.equals(classFilter.getSupportFragmentClass())) ||
                (classFilter.getFragmentClass().isAssignableFrom(view)          && !view.equals(classFilter.getFragmentClass()))        ||
                (classFilter.getViewHolderClass().isAssignableFrom(view)        && !view.equals(classFilter.getViewHolderClass()))      ||
                (classFilter.getViewClass().isAssignableFrom(view)              && !view.equals(classFilter.getViewClass()))
                ) {
            methodSet.addAll(Arrays.asList(view.getDeclaredMethods()));
            view = view.getSuperclass();
        }
        return methodSet;
    }


}