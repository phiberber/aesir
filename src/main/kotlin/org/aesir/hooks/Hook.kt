package org.aesir.hooks

import org.aesir.serialization.fromJson
import org.aesir.utils.url

lateinit var hook: Hook
lateinit var signatures: Signatures
lateinit var netvars: Netvars

class Hook(val timestamp: Int, val signatures: Signatures, val netvars: Netvars)

object Hooks {

    fun fetchData() {
        // https://raw.githubusercontent.com/frk1/hazedumper/master/csgo.min.json
        hook = """{"timestamp":1627476337,"signatures":{"anim_overlays":10624,"clientstate_choked_commands":19760,"clientstate_delta_ticks":372,"clientstate_last_outgoing_command":19756,"clientstate_net_channel":156,"convar_name_hash_table":192760,"dwClientState":5804012,"dwClientState_GetLocalPlayer":384,"dwClientState_IsHLTV":19784,"dwClientState_Map":652,"dwClientState_MapDirectory":392,"dwClientState_MaxPlayer":904,"dwClientState_PlayerInfo":21184,"dwClientState_State":264,"dwClientState_ViewAngles":19856,"dwEntityList":81408476,"dwForceAttack":52246276,"dwForceAttack2":52246288,"dwForceBackward":52246348,"dwForceForward":52246360,"dwForceJump":86298572,"dwForceLeft":52246384,"dwForceRight":52246372,"dwGameDir":6453120,"dwGameRulesProxy":86770364,"dwGetAllClasses":14360620,"dwGlobalVars":5803248,"dwGlowObjectManager":86947408,"dwInput":85936032,"dwInterfaceLinkList":9719348,"dwLocalPlayer":14197468,"dwMouseEnable":14220840,"dwMouseEnablePtr":14220792,"dwPlayerResource":52238992,"dwRadarBase":85819220,"dwSensitivity":14220484,"dwSensitivityPtr":14220440,"dwSetClanTag":565680,"dwViewMatrix":81349364,"dwWeaponTable":85938784,"dwWeaponTableIndex":12892,"dwYawPtr":14219912,"dwZoomSensitivityRatioPtr":14241064,"dwbSendPackets":882586,"dwppDirect3DDevice9":684112,"find_hud_element":1511061280,"force_update_spectator_glow":3864170,"interface_engine_cvar":256492,"is_c4_owner":3916480,"m_bDormant":237,"m_flSpawnTime":41840,"m_pStudioHdr":10572,"m_pitchClassPtr":85819888,"m_yawClassPtr":14219912,"model_ambient_min":5816420,"set_abs_angles":1968832,"set_abs_origin":1968384},"netvars":{"cs_gamerules_data":0,"m_ArmorValue":45948,"m_Collision":800,"m_CollisionGroup":1140,"m_Local":12220,"m_MoveType":604,"m_OriginalOwnerXuidHigh":12740,"m_OriginalOwnerXuidLow":12736,"m_SurvivalGameRuleDecisionTypes":4904,"m_SurvivalRules":3328,"m_aimPunchAngle":12332,"m_aimPunchAngleVel":12344,"m_angEyeAnglesX":45952,"m_angEyeAnglesY":45956,"m_bBombDefused":10672,"m_bBombPlanted":2469,"m_bBombTicking":10624,"m_bFreezePeriod":32,"m_bGunGameImmunity":14660,"m_bHasDefuser":45964,"m_bHasHelmet":45936,"m_bInReload":12965,"m_bIsDefusing":14640,"m_bIsQueuedMatchmaking":116,"m_bIsScoped":14632,"m_bIsValveDS":124,"m_bSpotted":2365,"m_bSpottedByMask":2432,"m_bStartedArming":13296,"m_bUseCustomAutoExposureMax":2521,"m_bUseCustomAutoExposureMin":2520,"m_bUseCustomBloomScale":2522,"m_clrRender":112,"m_dwBoneMatrix":9896,"m_fAccuracyPenalty":13104,"m_fFlags":260,"m_flC4Blow":10640,"m_flCustomAutoExposureMax":2528,"m_flCustomAutoExposureMin":2524,"m_flCustomBloomScale":2532,"m_flDefuseCountDown":10668,"m_flDefuseLength":10664,"m_flFallbackWear":12752,"m_flFlashDuration":42016,"m_flFlashMaxAlpha":42012,"m_flLastBoneSetupTime":10532,"m_flLowerBodyYawTarget":14992,"m_flNextAttack":11632,"m_flNextPrimaryAttack":12856,"m_flSimulationTime":616,"m_flTimerLength":10644,"m_hActiveWeapon":12024,"m_hBombDefuser":10676,"m_hMyWeapons":11768,"m_hObserverTarget":13196,"m_hOwner":10700,"m_hOwnerEntity":332,"m_hViewModel":13048,"m_iAccountID":12232,"m_iClip1":12900,"m_iCompetitiveRanking":6788,"m_iCompetitiveWins":7048,"m_iCrosshairId":46056,"m_iDefaultFOV":13100,"m_iEntityQuality":12204,"m_iFOVStart":12776,"m_iGlowIndex":42040,"m_iHealth":256,"m_iItemDefinitionIndex":12202,"m_iItemIDHigh":12224,"m_iMostRecentModelBoneCounter":9872,"m_iObserverMode":13176,"m_iShotsFired":41872,"m_iState":12888,"m_iTeamNum":244,"m_lifeState":607,"m_nBombSite":10628,"m_nFallbackPaintKit":12744,"m_nFallbackSeed":12748,"m_nFallbackStatTrak":12756,"m_nForceBone":9868,"m_nTickBase":13360,"m_nViewModelIndex":10688,"m_rgflCoordinateFrame":1092,"m_szCustomName":12348,"m_szLastPlaceName":13748,"m_thirdPersonViewAngles":12760,"m_vecOrigin":312,"m_vecVelocity":276,"m_vecViewOffset":264,"m_viewPunchAngle":12320,"m_zoomLevel":13264}}""".fromJson()

        hook.let {
            signatures = it.signatures
            netvars = it.netvars
        }
    }


}