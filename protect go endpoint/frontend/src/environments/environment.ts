// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

import {KeycloakService} from 'keycloak-angular';

export const environment = {
  production: false
};

export function initializer(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: {
            url: 'http://localhost:9080/auth',
            realm: 'poc',
            clientId: 'app-user'
          },
          initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: false,
            responseMode: 'fragment',
            flow: 'standard'
          }
        });
        resolve();
      } catch (error) {
        reject(error);
      }
    });
  };
}
