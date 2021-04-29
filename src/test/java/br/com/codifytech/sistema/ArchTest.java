package br.com.codifytech.sistema;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.codifytech.sistema");

        noClasses()
            .that()
            .resideInAnyPackage("br.com.codifytech.sistema.service..")
            .or()
            .resideInAnyPackage("br.com.codifytech.sistema.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.com.codifytech.sistema.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
