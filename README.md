[![Code Smells][code_smells_badge]][code_smells_link]
[![Maintainability Rating][maintainability_rating_badge]][maintainability_rating_link]
[![Security Rating][security_rating_badge]][security_rating_link]
[![Bugs][bugs_badge]][bugs_link]
[![Vulnerabilities][vulnerabilities_badge]][vulnerabilities_link]
[![Duplicated Lines (%)][duplicated_lines_density_badge]][duplicated_lines_density_link]
[![Reliability Rating][reliability_rating_badge]][reliability_rating_link]
[![Quality Gate Status][quality_gate_status_badge]][quality_gate_status_link]
[![Technical Debt][technical_debt_badge]][technical_debt_link]
[![Lines of Code][lines_of_code_badge]][lines_of_code_link]

Программа для загрузки множества версий мода на CurseForge и Modrinth. Исходя из имени файла, определяет версию
Minecraft и тип загрузчика (Forge, Fabric...), после чего загружает с нужными данными на платформу.

Идеальна для случая, когда нужно выпустить обновление на 18 версий майнкрафта, причём на несколько загрузчиков, а потом
лень сидеть и на протяжении двух часов тыкать по кнопкам, чтобы выгрузить все версии на площадке и проставить им
информацию и теги.

## Использование

Скачивается два файла: .jar и .json, это программа и настройки. Также необходимо создать рядом с ними папку "folders".

В папке "folders" создаётся папка, например, "The Rings of Power Mod". Имя может быть любое Это папка проекта, в которую
нужно положить
скомпилированные моды, которые предстоит загрузить на площадки.

Чтобы программа смогла загрузить файлы к нужному проекту, нужно, во-первых, указать токен, а во-вторых, связывать ID
проекта и имя ранее созданной папки. В .json-файле уже есть пример, как три идентификатора соответствуют трём именам
папок.

* Токен Modrinth можно получить здесь: https://modrinth.com/settings/pats.
* Токен CurseForge можно получить здесь: https://authors-old.curseforge.com/account/api-tokens.
* ID мода на Modrinth можно узнать, перейдя в управление проектом и скопировав в панели слева ID.
* ID мода на CurseForge можно узнать, перейдя в управление проектом и увидев число в адресной строке браузера.

Итак, настройка завершена, папка мода создана. Теперь программа способна загружать файлы к проекту. Чтобы файлы
загрузились правильно, нужно давать своим скомпилированным файлам модов строго следующий шаблон имени:

`[1.3.2] API Upload Test 24.02.29 (Forge).jar`

В квадратных скобках указана версия игры, в круглых - загрузчик.

После этого можно запустить программу из консоли, например `java -jar appCurseForge.jar`

<!----------------------------------------------------------------------------->

[code_smells_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=code_smells

[code_smells_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[maintainability_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=sqale_rating

[maintainability_rating_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[security_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=security_rating

[security_rating_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[bugs_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=bugs

[bugs_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[vulnerabilities_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=vulnerabilities

[vulnerabilities_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[duplicated_lines_density_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=duplicated_lines_density

[duplicated_lines_density_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[reliability_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=reliability_rating

[reliability_rating_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[quality_gate_status_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=alert_status

[quality_gate_status_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[technical_debt_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=sqale_index

[technical_debt_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader

[lines_of_code_badge]: https://sonarcloud.io/api/project_badges/measure?project=hummel009_Minecraft-Mod-Platforms-Uploader&metric=ncloc

[lines_of_code_link]: https://sonarcloud.io/summary/overall?id=hummel009_Minecraft-Mod-Platforms-Uploader
