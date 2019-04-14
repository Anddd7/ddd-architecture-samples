package architechture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import study.huhao.demo.domain.core.HumbleObject;
import study.huhao.demo.domain.core.Repository;
import study.huhao.demo.infrastructure.persistence.PersistenceDto;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.theClass;

class PersistenceLayerTest {
    private JavaClasses classes;

    @BeforeEach
    void setUp() {
        classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("study.huhao.demo");
    }

    @Nested
    class repository_impl {

        @Test
        void repository_impls_be_public() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().implement(Repository.class)
                    .should().bePublic()
                    .as("The repository impls should be package private.")
                    .check(classes);
        }

        @Test
        void repository_impls_be_annotated_with_Component() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().implement(Repository.class)
                    .should().beAnnotatedWith(Component.class)
                    .as("The repository impls should be annotated with 'Component'.")
                    .check(classes);
        }

        @Test
        void repository_impls_should_be_named_ending_with_RepositoryImpl() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().implement(Repository.class)
                    .should().haveSimpleNameEndingWith("RepositoryImpl")
                    .as("The repository impls should be named ending with 'RepositoryImpl'.")
                    .check(classes);
        }

        @Test
        void repository_impls_should_implement_Repository() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().haveSimpleNameEndingWith("RepositoryImpl")
                    .should().implement(Repository.class)
                    .as("The repository impls should implement 'Repository' interface.")
                    .check(classes);
        }
    }

    @Nested
    class jpa_repository {

        @Test
        void jpa_repositories_be_package_private() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().areAssignableTo(JpaRepository.class)
                    .should().bePackagePrivate()
                    .as("The jpa repositories should be package private.")
                    .check(classes);
        }

        @Test
        void jpa_repositories_be_annotated_with_Spring_Repository() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().areAssignableTo(JpaRepository.class)
                    .should().beAnnotatedWith(org.springframework.stereotype.Repository.class)
                    .as("The jpa repositories should be annotated with Spring's 'Repository'.")
                    .check(classes);
        }

        @Test
        void jpa_repositories_should_be_named_ending_with_Jpa() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().areAssignableTo(JpaRepository.class)
                    .should().haveSimpleNameEndingWith("JpaRepository")
                    .as("The jpa repositories should be named ending with 'JpaRepository'.")
                    .check(classes);
        }

        @Test
        void jpa_repositories_should_implement_JpaRepository() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().haveSimpleNameEndingWith("JpaRepository")
                    .should().beAssignableTo(JpaRepository.class)
                    .as("The jpa repositories should implement 'JpaRepository' interface.")
                    .check(classes);
        }
    }

    @Nested
    class persistence_dto {

        @Test
        void PersistenceDto_interface_should_extend_HumbleObject() {
            theClass(PersistenceDto.class)
                    .should().beAssignableTo(HumbleObject.class)
                    .as("The PersistenceDto interface should extend HumbleObject.")
                    .check(classes);
        }

        @Test
        void persistence_dtos_be_package_private() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().implement(PersistenceDto.class)
                    .should().bePackagePrivate()
                    .as("The persistence DTOs should be package private.")
                    .check(classes);
        }

        @Test
        void persistence_dtos_should_be_named_ending_with_Persistence() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().implement(PersistenceDto.class)
                    .should().haveSimpleNameEndingWith("Dto")
                    .as("The persistence DTOs should be named ending with 'Persistence'.")
                    .check(classes);
        }

        @Test
        void persistence_dtos_should_implement_PersistenceDto() {
            classes()
                    .that().resideInAPackage("..persistence..")
                    .and().haveSimpleNameEndingWith("Dto")
                    .and().areNotInterfaces()
                    .should().implement(PersistenceDto.class)
                    .as("The persistence DTOs should implement 'PersistenceDto' interface.")
                    .check(classes);
        }
    }
}