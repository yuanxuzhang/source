package swip.ch18datepicker.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface ExplicitWait extends SearchScope {

    default Element untilFound(Supplier<By> by) {
        Element element = new FluentWait<>(this)
            .withTimeout(10, SECONDS)
            .pollingEvery(5, MILLISECONDS)
            .ignoring(Exception.class)
            .until((ExplicitWait e) -> e.findElement(by));
        element.setSearchContext(this);
        element.setLocator((SearchScope e) -> this.untilFound(by));
        return element;
    }

    String getText();

    default void until(Predicate<ExplicitWait> predicate) {
        FluentWait<ExplicitWait> fluentWait = new FluentWait<>(this)
            .withTimeout(10, SECONDS)
            .pollingEvery(100, MILLISECONDS)
            .ignoring(NoSuchElementException.class);
        fluentWait.until(
            (ExplicitWait where) -> predicate.test(where)
        );
    }

}