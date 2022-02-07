call cd keycloak-16.1.1/bin
call standalone.bat -b localhost -Dkeycloak.frontendUrl=http://localhost:8083/auth -Dkeycloak.profile.feature.upload_scripts=enabled -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=../../auth/src/main/resources/realms.json -Dkeycloak.migration.strategy=IGNORE_EXISTING
