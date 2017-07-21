import { IGroup } from './igroup';

export interface IDisciplines {
  idNames: IDiscipline[];
};

export interface IDiscipline {
  id: number;
  name: string;
  groups: IGroup;
}

