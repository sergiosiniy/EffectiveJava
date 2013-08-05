/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.suobig.effectivejava.createAndDelete;

/**
 *
 * @author popov
 */
public class U3Singleton {
/*
Синглтон - это класс, реализация которого гарантирует существование не более 
одного экземпляра. Чаще всего синглтон представляют собой системные компоненты,
которые по логике уникальны: менеджер окон, файловая система и т.д.

  До версии 1.5 существовало 2 способа объявить синглтон: 
  * 1 Публичное статическое поле и private конструктор*/
    public static class  SaticFieldSingleton {
        public static final SaticFieldSingleton INSTANCE = 
            new SaticFieldSingleton();
        private SaticFieldSingleton() {};
    }
/* Так как публичный конструктор отствует, единственный экземлпяр этого класса 
 будет существовать в поле INSTANCE и создание других будет невозможно. 
 Существует, однако, уязвимость: при наличии соответствующих привелегий можно 
 вызывать приватный конструктор через reflection используя метод
 AccessibleObject.setAccessible. Для защиты от подобной атаки следует в 
 конструкторе прописать выбрасывание исключения при попытке создать еще один
 экземпляр.
 
 * 2 Private поле и public static factory метод*/
    public static class StaticFactorySingleton {
        private static final StaticFactorySingleton INSTANCE = 
                new StaticFactorySingleton();
        private StaticFactorySingleton() { }
        public static StaticFactorySingleton getInstance() { return INSTANCE; }

/* Все вызовы StaticFactorySingleton.getInstance() вернут ссылки на один и тот
 же объект и создание нового невозможно (с учетом замечания выше)
 
 Достоинства первого метода - из листинга класса сразу очевидно, что он явлется
синглтоном.
 
Достоинства второго - возможность в любой момент изменить механику работы 
фабрики, чтобы класс перестал быть синглтоном. 
 
  Однако для того, чтобы синглтон, реализованный любым из двух перечисленных 
выше методов поддерживал сериализацию, недостаточно просто в объявлении класса 
декларировать implements Serializable. Чтобы класс продолжал оставаться 
синглтоном, необходимо декларировать все его поля как transient и реализовать
метод readResolve():*/
        private Object readResolve() {
            return INSTANCE;
        }
    }
 /* Начиная с релиза 1.5 появился третий подход к реализации синглтона через 
Enum:*/
    public enum EnumSingleton {
        INSTANCE;
    }
 /* Этот подход анлогичен подходу с публичным полем, с той разницей, что короче
в записи, автоматически поддерживает сериализацию и предоставляет 
железобетонные гарантии защиты от атак через reflection или сериализацию.

Не смотря на то, что этот подход пока что широко не используется, это луший
способ реализовать синглтон.
  */
}
